package com.bms.notification_v1_apis.model;

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
