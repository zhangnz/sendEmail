package com.qimeng.tools.module.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by nianzhong on 2020/8/2.
 */
public interface EmailService {

    void sendEmail(String receiver) throws MessagingException;

    List<String> findReceivers() throws IOException;
}
