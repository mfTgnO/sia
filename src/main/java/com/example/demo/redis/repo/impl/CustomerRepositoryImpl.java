package com.example.demo.redis.repo.impl;

import com.example.demo.redis.model.Customer;
import com.example.demo.redis.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String KEY = "Customer";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Customer> hashOperations;

    @Autowired
    public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Customer customer) {
        hashOperations.put(KEY, customer.getId(), customer);
    }

    @Override
    public Customer find(Long id) {
        return hashOperations.get(KEY, id);
    }

    @Override
    public Map<Long, Customer> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void update(Customer customer) {
        hashOperations.put(KEY, customer.getId(), customer);
    }

    @Override
    public void delete(Long id) {
        hashOperations.delete(KEY, id);
    }
}
