package com.qimeng.tools.core.schedule;

import com.qimeng.tools.module.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nianzhong on 2020/8/2.
 */

@Component
public class SendEmialSchedule {

    /**
     * 每天9点发送邮件
     */
    @Scheduled(cron="0 0 10 * * 1,3,5")
    //@Scheduled(cron="0 * * * * ?")
    public void sendEmail() {

        logger.info("**************[定时任务]开始发送邮件***************");

        List<String> list = null;
        try {
            list = emailService.findReceivers();
        } catch (IOException e) {
            logger.error("**************[定时任务]查询excel收件人信息失败:{}",e.getMessage());
            return;
        }
        list.forEach(reciever -> {
            try {
                emailService.sendEmail(reciever);
            } catch (MessagingException e) {
                logger.error("**************[定时任务]发送给[{}]的邮件失败:{}",reciever,e.getMessage());
            }
        });

    }

    private static Logger logger = LoggerFactory.getLogger(SendEmialSchedule.class);
    @Autowired
    private EmailService emailService;
}
