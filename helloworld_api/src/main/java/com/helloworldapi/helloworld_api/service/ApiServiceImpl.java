package com.helloworldapi.helloworld_api.service;

import org.springframework.stereotype.Service;

import com.helloworldapi.helloworld_api.repository.ApiRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApiServiceImpl implements ApiService{
    ApiRepository apiRepository;

    public String createMessage(String name){
        return apiRepository.createMessage(name);
    }

    
    
}
