package com.qimeng.tools;

import com.qimeng.tools.module.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by nianzhong on 2020/8/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    static Logger logger = LoggerFactory.getLogger(MyTest.class);

    @Autowired
    private EmailService emailService;


    @Test
    public void email() {
        logger.info("**************[手动触发]开始发送邮件***************");

        List<String> list = null;
        try {
            list = emailService.findReceivers();
        } catch (IOException e) {
            logger.error("**************[手动触发]查询excel收件人信息失败:{}",e.getMessage());
            return;
        }
        list.forEach(reciever -> {
            try {
                emailService.sendEmail(reciever);
            } catch (MessagingException e) {
                logger.error("**************[手动触发]发送给[{}]的邮件失败:{}",reciever,e.getMessage());
            }
        });

    }
}
