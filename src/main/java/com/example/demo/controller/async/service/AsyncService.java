package com.example.demo.controller.async.service;

import com.example.demo.controller.async.model.EmployeeAddresses;
import com.example.demo.controller.async.model.EmployeeNames;
import com.example.demo.controller.async.model.EmployeePhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author rnj
 */
@Service
@Slf4j
public class AsyncService {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeNames> getEmployeeNames() throws InterruptedException {
        log.info("getEmployeeName Starts");
        EmployeeNames employeeNameData = restTemplate.getForObject("http://localhost:8080/name", EmployeeNames.class);

        log.info("employeeNameData, {}", employeeNameData);
        //Intentional delay
        Thread.sleep(1000L);

        log.info("employeeNameData completed");
        return CompletableFuture.completedFuture(employeeNameData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeAddresses> getEmployeeAddresses() throws InterruptedException {
        log.info("getEmployeeAddress Starts");
        EmployeeAddresses employeeAddressData = restTemplate.getForObject("http://localhost:8080/address", EmployeeAddresses.class);

        log.info("employeeAddressData, {}", employeeAddressData);
        //Intentional delay
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(employeeAddressData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException {
        log.info("getEmployeePhone Starts");
        EmployeePhone employeePhoneData = restTemplate.getForObject("http://localhost:8080/phone", EmployeePhone.class);

        log.info("employeePhoneData, {}", employeePhoneData);
        //Intentional delay
        Thread.sleep(1000L);

        log.info("employeePhoneData completed");
        return CompletableFuture.completedFuture(employeePhoneData);
    }
}