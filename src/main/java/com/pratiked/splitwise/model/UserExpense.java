package com.pratiked.splitwise.model;

import com.pratiked.splitwise.model.constants.UserExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_expense")
public class UserExpense extends BaseModel{

    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
