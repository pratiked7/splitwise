package com.pratiked.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String phone;
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;
}
