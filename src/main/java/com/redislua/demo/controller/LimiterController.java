package com.redislua.demo.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redislua.demo.limit.LimitType;
import com.redislua.demo.limit.annotation.Limit;

/**
 * Created on 2020-06-28
 *
 * @author chen qi
 */
@RestController
public class LimiterController {
    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger(1);
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger(2);
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger(3);

    /**
     * @author chen qi
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "limitTest", period = 5, count = 3)
    @GetMapping("/limitTest1")
    public int testLimiter1() {

        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    /**
     * @author chen qi
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "customer_limit_test", period = 10, count = 3, limitType = LimitType.CUSTOMER)
    @GetMapping("/limitTest2")
    public int testLimiter2() {

        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    /**
     * @author chen qi
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "ip_limit_test", period = 10, count = 3, limitType = LimitType.IP)
    @GetMapping("/limitTest3")
    public int testLimiter3() {

        return ATOMIC_INTEGER_3.incrementAndGet();
    }
}
