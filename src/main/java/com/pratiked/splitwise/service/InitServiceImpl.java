package com.pratiked.splitwise.service;

import com.pratiked.splitwise.model.Expense;
import com.pratiked.splitwise.model.Group;
import com.pratiked.splitwise.model.User;
import com.pratiked.splitwise.model.UserExpense;
import com.pratiked.splitwise.model.constants.Currency;
import com.pratiked.splitwise.model.constants.UserExpenseType;
import com.pratiked.splitwise.repository.ExpenseRepository;
import com.pratiked.splitwise.repository.GroupRepository;
import com.pratiked.splitwise.repository.UserExpenseRepository;
import com.pratiked.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public void initialise() {

        Group group = new Group();
        group.setName("Goa Trip");
        group.setDescription("Goa trip 4 users");
        group.setDefaultCurrency(Currency.INR);
        Group savedGroup = groupRepository.save(group);

        User userA = User.builder().name("A").email("a@gmail.com").phone("1234").groups(List.of(savedGroup)).build();
        User userB = User.builder().name("B").email("b@gmail.com").phone("1234").groups(List.of(savedGroup)).build();
        User userC = User.builder().name("C").email("c@gmail.com").phone("1234").groups(List.of(savedGroup)).build();
        User userD = User.builder().name("D").email("d@gmail.com").phone("1234").groups(List.of(savedGroup)).build();
        User userE = User.builder().name("E").email("e@gmail.com").phone("1234").groups(List.of(savedGroup)).build();
        User userF = User.builder().name("F").email("f@gmail.com").phone("1234").groups(List.of(savedGroup)).build();

        User savedUserA = userRepository.save(userA);
        User savedUserB = userRepository.save(userB);
        User savedUserC = userRepository.save(userC);
        User savedUserD = userRepository.save(userD);
        User savedUserE = userRepository.save(userE);
        User savedUserF = userRepository.save(userF);

        savedGroup.setUsers(List.of(savedUserA, savedUserB, savedUserC, savedUserD, savedUserE, savedUserF));
        groupRepository.save(savedGroup);

        UserExpense userExpenseA = new UserExpense();
        userExpenseA.setUserExpenseType(UserExpenseType.OWE);
        userExpenseA.setAmount(500);
        userExpenseA.setUser(userA);
        UserExpense savedUserExpenseA = userExpenseRepository.save(userExpenseA);

        UserExpense userExpenseB = new UserExpense();
        userExpenseB.setUserExpenseType(UserExpenseType.OWE);
        userExpenseB.setAmount(2000);
        userExpenseB.setUser(userB);
        UserExpense savedUserExpenseB = userExpenseRepository.save(userExpenseB);

        UserExpense userExpenseC = new UserExpense();
        userExpenseC.setUserExpenseType(UserExpenseType.OWE);
        userExpenseC.setAmount(500);
        userExpenseC.setUser(userC);
        UserExpense savedUserExpenseC = userExpenseRepository.save(userExpenseC);

        UserExpense userExpenseD = new UserExpense();
        userExpenseD.setUserExpenseType(UserExpenseType.PAID);
        userExpenseD.setAmount(1500);
        userExpenseD.setUser(userD);
        UserExpense savedUserExpenseD = userExpenseRepository.save(userExpenseD);

        UserExpense userExpenseE = new UserExpense();
        userExpenseE.setUserExpenseType(UserExpenseType.PAID);
        userExpenseE.setAmount(500);
        userExpenseE.setUser(userE);
        UserExpense savedUserExpenseE = userExpenseRepository.save(userExpenseE);

        UserExpense userExpenseF = new UserExpense();
        userExpenseF.setUserExpenseType(UserExpenseType.PAID);
        userExpenseF.setAmount(1000);
        userExpenseF.setUser(userF);
        UserExpense savedUserExpenseF = userExpenseRepository.save(userExpenseF);

        Expense expense = new Expense();
        expense.setDescription("Total trip expense");
        expense.setAmount(3000);
        expense.setCurrency(Currency.INR);
        expense.setUserExpenses(List.of(savedUserExpenseA, savedUserExpenseB,savedUserExpenseC, savedUserExpenseD, savedUserExpenseE, savedUserExpenseF));

        Expense savedExpense = expenseRepository.save(expense);

        savedGroup.setExpenses(List.of(savedExpense));
        groupRepository.save(savedGroup);


    }
}
