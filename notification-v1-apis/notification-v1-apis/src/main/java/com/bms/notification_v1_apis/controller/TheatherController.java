package com.bms.notification_v1_apis.controller;

import com.bms.notification_v1_apis.RequestBody.TheatherRequestRb;
import com.bms.notification_v1_apis.service.TheatherMailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/v1/notify/theather")
public class TheatherController {
    @Autowired
     TheatherMailService theatherMailService;

    @PutMapping("/request")
    public void notifyAdminForCreateTheatherRequest(@RequestBody TheatherRequestRb theatherRequestRb) throws MessagingException {
        System.out.println("____________hit ______________________");
        theatherMailService.notifyAdminForCreateTheatherRequest(theatherRequestRb);
    }
}
