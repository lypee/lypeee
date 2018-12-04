package com.lypee.service.impl;

import checkcode.patchca.utils.Constants;
import com.lypee.dao.PayMethodDao;
import com.lypee.service.PayMethodService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("payMethodService")
public class PayMethodServiceImpl implements PayMethodService {
        PayMethodDao payMethodDao ;
    @Override
    public void addPayMethod(int userId, String payMethodName, int isCountInThisMonthEx, String inOrEx, String remark) {
        Map<String ,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("name",payMethodName);
        map.put("isCountInThisMonthEx", isCountInThisMonthEx == -1 ? null : isCountInThisMonthEx);
        map.put("inOrEx", inOrEx);
        map.put("remark", remark);
        map.put("dele", Constants.NOT_DELE);
        payMethodDao.insert(map) ;
    }
}
