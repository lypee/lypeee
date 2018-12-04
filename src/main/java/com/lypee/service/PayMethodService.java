package com.lypee.service;

public interface PayMethodService {
    void addPayMethod(int userId ,String payMethodName ,int isCountInThisMonthEx ,String inOrEx ,String remark);
}
