package com.csii.controller;

import com.csii.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 测试redis分布式锁Controller
 * @author: chengyu
 * @date: 2026-03-01 14:50
 */
@RestController
@Slf4j
public class TestLockController {

    @Autowired
    private RedisService redisService;

    private static final String LOCK_KEY = "LOCK:EXECUTE:METHOD";

    @GetMapping("/testLock")
    public void callService() {
        Thread t1 = new Thread(() -> method2(), "thread1");
        Thread t2 = new Thread(() -> method2(), "thread2");
        Thread t3 = new Thread(() -> method2(), "thread3");

        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 尝试获取到锁才执行，给定一个等待获取锁的时间(2分钟)
     */
    public void method2() {
        try {
            boolean acquire = redisService.tryLock(LOCK_KEY, 120);
            if (!acquire) {
                log.info("redis service try lock failed, method not execute and quit.");
                return;
            }

            log.info("method {} begin execute...", Thread.currentThread().getName());
            Thread.sleep(10000);
            log.info("method {} execute over", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisService.unlock(LOCK_KEY);
        }
    }

    /**
     * 获取到锁才执行
     */
    public void method() {
        try {
            redisService.lock(LOCK_KEY);
            log.info("method {} begin execute...", Thread.currentThread().getName());
            Thread.sleep(20000);
            log.info("method {} execute over", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisService.unlock(LOCK_KEY);
        }
    }
}
