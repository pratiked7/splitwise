package com.pratiked.splitwise.service.strategy;

import com.pratiked.splitwise.dto.TransactionDTO;
import com.pratiked.splitwise.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}
