package com.pratiked.splitwise.service.strategy;

import com.pratiked.splitwise.dto.TransactionDTO;
import com.pratiked.splitwise.model.Expense;
import com.pratiked.splitwise.model.User;
import com.pratiked.splitwise.model.UserExpense;
import com.pratiked.splitwise.model.constants.UserExpenseType;

import java.util.*;

public class HeapBasedSettleUpStrategy implements SettleUpStrategy{

    @Override
    public List<TransactionDTO> settleUp(List<Expense> expenses) {

        HashMap<User, Double> outstandingAmountMap = new HashMap<>();
        List<TransactionDTO> transactions = new ArrayList<>();

        for (Expense expense: expenses) {
            for (UserExpense userExpense: expense.getUserExpenses()) {
                User user = userExpense.getUser();

                double currentOutstandingAmount = outstandingAmountMap.getOrDefault(user, 0.00);
                outstandingAmountMap.put(user, getUpdatedOutstandingAmount(currentOutstandingAmount, userExpense));
            }
        }

        //max heap
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        
        //min heap
        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        for (Map.Entry<User, Double> entry: outstandingAmountMap.entrySet()){
            if (entry.getValue() < 0){
                minHeap.add(entry);
            } else if(entry.getValue() > 0){
                maxHeap.add(entry);
            } else {
                System.out.println(entry.getKey().getName() + "'s is already settled");
            }
        }


        return transactions;
    }

    private double getUpdatedOutstandingAmount(double currentOutstandingAmount, UserExpense userExpense){
        if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
            currentOutstandingAmount = currentOutstandingAmount + userExpense.getAmount();
        } else {
            currentOutstandingAmount = currentOutstandingAmount - userExpense.getAmount();
        }
        return currentOutstandingAmount;
    }
}
