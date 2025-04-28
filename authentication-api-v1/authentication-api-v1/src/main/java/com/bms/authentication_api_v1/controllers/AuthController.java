package com.bms.authentication_api_v1.controllers;

import com.bms.authentication_api_v1.ResponseBody.SuccessResponseBody;
import com.bms.authentication_api_v1.ResponseBody.TokenResponseBody;
import com.bms.authentication_api_v1.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apis/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/token")
    public ResponseEntity getTokens(@RequestParam String email, @RequestParam String password){
        String tokens= authService.generateToken(email,password);
        TokenResponseBody response= new TokenResponseBody();
        response.setTokens(tokens);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify-token")
    public ResponseEntity verifyToken(){
        SuccessResponseBody successRB = new SuccessResponseBody();
        successRB.setStatus("Success");
        return new ResponseEntity(successRB, HttpStatus.OK);

    }



}
