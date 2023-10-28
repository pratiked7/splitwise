package com.pratiked.splitwise.service.strategy;

public class SettleUpStrategyFactory {
    //TODO: add enum for different strategies

    public static SettleUpStrategy getSettleUpStrategy(){
        return new HeapBasedSettleUpStrategy();
    }
}
