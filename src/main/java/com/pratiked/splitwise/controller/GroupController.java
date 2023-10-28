package com.pratiked.splitwise.controller;

import com.pratiked.splitwise.dto.TransactionDTO;
import com.pratiked.splitwise.exception.GroupNotFoundException;
import com.pratiked.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/settleUp/{groupId}")
    public ResponseEntity settleUpForGroup(@PathVariable int groupId) throws GroupNotFoundException {
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId);

        return ResponseEntity.ok(transactions);
    }

}
