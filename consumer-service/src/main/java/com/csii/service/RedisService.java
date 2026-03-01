package com.csii.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description com.csii.service
 * @author: chengyu
 * @date: 2026-03-01 14:18
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁
     * 如果锁不可用，则当前线程将无法参与线程调度，并处于休眠状态，直至获取该锁。
     * 实现注意事项 锁的实现可能能够检测到锁的错误使用，例如可能导致死锁的调用，并可能在这种情况下抛出（未检查的）异常。
     * 该锁实现必须记录这些情况和异常类型。
     */
    public void lock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock();
        } catch (Exception e) {
            log.error("redis service lock error", e);
            throw e;
        }
    }

    /**
     * 尝试获取锁
     * 仅在调用时锁处于空闲状态时才获取该锁。
     * 如果锁可用，则获取该锁并立即返回true值。如果锁不可用，则此方法将立即返回false值。
     *
     * @param lockKey
     * @return 返回值：若已获取锁，则返回true；否则返回false
     */
    public boolean tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    /**
     * 尝试获取锁，给定一个等待时间
     * 如果在给定的等待时间内锁处于空闲状态，且当前线程未被中断，则获取该锁。
     *
     * @param lockKey
     * @param waitTime 等待时间，单位s
     *
     * @return 如果获取了锁，则返回值为true。 如果获取锁的过程中等待且指定的等待时间已过，则返回值为false
     */
    public boolean tryLock(String lockKey, long waitTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("redis service trylock error", e);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @return
     */
    public boolean unlock(String lockKey) {
        RLock rLock = redissonClient.getLock(lockKey);
        if (rLock != null && rLock.isLocked()) {
            rLock.unlock();
            return true;
        }
        return false;
    }
}
