package com.pratiked.splitwise.service;

import com.pratiked.splitwise.dto.TransactionDTO;
import com.pratiked.splitwise.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException;
}
