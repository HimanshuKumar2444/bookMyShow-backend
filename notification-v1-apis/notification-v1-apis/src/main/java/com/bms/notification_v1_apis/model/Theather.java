package com.bms.notification_v1_apis.model;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Theather {
    UUID id;
    String name;
    String address;
    int pinCode;
    String state;
    String Status;
    AppUser owner;
}
