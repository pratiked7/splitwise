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

        while (!minHeap.isEmpty()){

            Map.Entry<User, Double> maxOwe = minHeap.poll();
            Map.Entry<User, Double> maxGetBack = maxHeap.poll();

            TransactionDTO transactionDTO = new TransactionDTO(
                    maxOwe.getKey().getName(),
                    maxGetBack.getKey().getName(),
                    Math.min(Math.abs(maxOwe.getValue()), maxGetBack.getValue()));

            transactions.add(transactionDTO);

            double newBalance = maxOwe.getValue() + maxGetBack.getValue();
            if(newBalance == 0){
                System.out.println("Settled between " + maxOwe.getKey().getName() + " and " + maxGetBack.getKey().getName());
            } else if (newBalance < 0){
                maxOwe.setValue(newBalance);
                minHeap.add(maxOwe);
            } else if(newBalance > 0){
                maxGetBack.setValue(newBalance);
                maxHeap.add(maxGetBack);
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
