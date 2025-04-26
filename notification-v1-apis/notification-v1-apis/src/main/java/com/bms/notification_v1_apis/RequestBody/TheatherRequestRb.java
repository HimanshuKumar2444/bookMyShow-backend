package com.bms.notification_v1_apis.RequestBody;

import com.bms.notification_v1_apis.model.AppUser;
import com.bms.notification_v1_apis.model.Theather;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TheatherRequestRb {
    Theather theather;
    AppUser Admin;

}
