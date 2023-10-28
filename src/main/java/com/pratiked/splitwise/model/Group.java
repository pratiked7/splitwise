package com.pratiked.splitwise.model;

import com.pratiked.splitwise.model.BaseModel;
import com.pratiked.splitwise.model.constants.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "group")
public class Group extends BaseModel {
    private String name;
    private String description;
    private double totalAmountSpent;

    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;
    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Expense> expenses;
    @ManyToMany
    private List<User> users;

}
