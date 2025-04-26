package com.bms.notification_v1_apis.controller;

import com.bms.notification_v1_apis.RequestBody.NotificationMessage;
import com.bms.notification_v1_apis.RequestBody.TheatherRequestRb;
import com.bms.notification_v1_apis.service.TheatherMailService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RabbitMqController {

    @Autowired
    ModelMapper mapper=new ModelMapper();
    @Autowired
    TheatherMailService theatherMailService;

    @RabbitListener(queues = "bms-notification-queue")
    public void consumeMessage(@Payload NotificationMessage message)throws Exception{
         String messagetype= message.getMessageType();

         if(messagetype.equals("Create_Theater")){
           Object payload=message.getPayload();
         TheatherRequestRb requestRb=  mapper.map(payload, TheatherRequestRb.class);
         log.info(requestRb.toString());
         theatherMailService.notifyAdminForCreateTheatherRequest(requestRb);

         }
    }

}
