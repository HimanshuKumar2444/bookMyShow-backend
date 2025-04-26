package com.bms.notification_v1_apis.service;

import com.bms.notification_v1_apis.RequestBody.TheatherRequestRb;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class TheatherMailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;

    public  void  notifyAdminForCreateTheatherRequest(TheatherRequestRb theatherRequestRb) throws MessagingException {
        Context context=new Context();
        context.setVariable("adminName",theatherRequestRb.getAdmin().getName());
        context.setVariable("theaterName",theatherRequestRb.getTheather().getName());
        context.setVariable("address",theatherRequestRb.getTheather().getAddress());
        context.setVariable("state",theatherRequestRb.getTheather().getState());
        context.setVariable("pincode",theatherRequestRb.getTheather().getPinCode());
        context.setVariable("ownerName",theatherRequestRb.getTheather().getOwner().getName());
        context.setVariable("ownerEmail",theatherRequestRb.getTheather().getOwner().getEmail());
//        converting all the context variable into html template..
        String htmlEmail=templateEngine.process("theatherRequest",context);

//        meme message..-> showing on mail why sending this mail to you
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("A new theater has registered and is awaiting your approval.");
        mimeMessageHelper.setTo(theatherRequestRb.getAdmin().getEmail());
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(mimeMessage);




    }

}
