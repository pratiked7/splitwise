package com.pratiked.splitwise.model;

import com.pratiked.splitwise.model.constants.UserExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_expenses")
public class UserExpense extends BaseModel{

    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
