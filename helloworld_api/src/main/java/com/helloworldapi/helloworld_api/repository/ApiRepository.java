package com.helloworldapi.helloworld_api.repository;



import org.springframework.stereotype.Repository;

@Repository
public class ApiRepository {

    public String createMessage(String name){
        return "Hello " + name + ", hope you are having a great day";

    }
    
}
