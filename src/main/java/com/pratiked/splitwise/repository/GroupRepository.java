package com.pratiked.splitwise.repository;

import com.pratiked.splitwise.model.Group;
import com.pratiked.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
