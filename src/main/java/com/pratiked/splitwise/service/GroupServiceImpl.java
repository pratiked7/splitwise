package com.pratiked.splitwise.service;

import com.pratiked.splitwise.dto.TransactionDTO;
import com.pratiked.splitwise.exception.GroupNotFoundException;
import com.pratiked.splitwise.model.Group;
import com.pratiked.splitwise.repository.GroupRepository;
import com.pratiked.splitwise.service.strategy.SettleUpStrategy;
import com.pratiked.splitwise.service.strategy.SettleUpStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException {
        SettleUpStrategy strategy = SettleUpStrategyFactory.getSettleUpStrategy();
        Optional<Group> savedGroup = groupRepository.findById(groupId);
        if(savedGroup.isEmpty()){
            throw new GroupNotFoundException("Group not found");
        }
        List<TransactionDTO> transactions = strategy.settleUp(savedGroup.get().getExpenses());
        return transactions;
    }
}
