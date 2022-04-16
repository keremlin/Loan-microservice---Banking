package com.tosan.loan.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositResourcesImpl implements DepositResources {

    @Autowired
    private RestTemplate rest;

    @Value("${com.tosan.deposit}")
    private String depositURL;
    @Value("${com.tosan.customer}")
    private String customerURL;
    @Value("${com.tosan.transaction}")
    private String transactionURL;

    @Override
    public boolean isDepositValid(String depositNumber) {
        return rest.getForObject("http://" + depositURL + "/api/deposit/" + depositNumber + "/isValid", Boolean.class);
    }
    
    @Override
    public boolean withdrawInstallment(String depositNumber, int amount) {
        return rest.getForObject("http://" + depositURL + "/api/deposit/" + depositNumber + "/" + amount + "/withdrawInstallment",
                Boolean.class);
    }

}
