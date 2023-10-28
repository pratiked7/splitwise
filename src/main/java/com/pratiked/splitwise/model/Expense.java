package com.pratiked.splitwise.model;

import com.pratiked.splitwise.model.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense extends BaseModel{
    private double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToMany
    private List<UserExpense> userExpenses;
}
