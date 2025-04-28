package com.bms.authentication_api_v1.integrations;

import com.bms.authentication_api_v1.Models.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
//import java.util.UUID;

@Service
public class DbApi extends RestApiForCrudOperation{
    @Value("${db.api.base}")
    String baseUrl;
    @Autowired
    ModelMapper modelMapper;


    public AppUser getUserById(String email){
        String endpoint="/user/getbyemail/"+email;
        Object response= makeGetCall(baseUrl,endpoint,new HashMap<>());
        if(response==null){
            return null;
        }
        AppUser userres= modelMapper.map(response,AppUser.class);
        System.out.println(userres);
        return  userres;
    }

//    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaW1hbnNodWt1bWFyNTIwMDQ3QGdtYWlsLmNvbTpTZWN1cmVQYXNzQDEyMyIsImV4cCI6MTc0NTg3NDM4NywiaWF0IjoxNzQ1ODczMzg3fQ.5Mkv4LMcrVkUGW0gBXPyPorPSBMcSzizALNOnGluSRQ
}
