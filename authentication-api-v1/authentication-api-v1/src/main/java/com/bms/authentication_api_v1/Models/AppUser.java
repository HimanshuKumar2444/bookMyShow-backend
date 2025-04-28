package com.bms.authentication_api_v1.Models;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AppUser {
    private UUID id;
    private  String name;
    private  String email;
    private  String password;
    private  Long phoneNumber;
    private  String address;
    private  String state;
    private int pincode;
    private String userType;
}
