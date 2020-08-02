package com.qimeng.tools.module.service.impl;

import com.qimeng.tools.core.utils.ExcelUtils;
import com.qimeng.tools.module.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianzhong on 2020/8/2.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.attach-file}")
    private String attachFile;

    @Value("${spring.mail.receiver-file}")
    private String receiverFile;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String receiver) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setSubject("商业计划书（北京启梦驰游科技有限公司）");
        helper.setFrom("启梦驰游<"+sender+">");
        helper.setTo(receiver);

        //抄送人员
        //helper.setCc("");

        //邮件内容
        String line = "<br/>";
        String head = "&nbsp;&nbsp;&nbsp;&nbsp;";
        //String head = "    ";
        StringBuffer text = new StringBuffer();
        text.append("你好：").append(line);
        text.append(head).append("首先，感谢贵风投公司看到我公司的邮件。").append(line);
        text.append(head).append("我公司坐落于首都北京朝阳区，成立于2020年，专注开发一款出海类精品休闲三消PRG（角色扮演）原力觉醒 手机游戏，游戏研发团队研发技术经验丰富。现在正寻求融资，资金用于游戏的研发，希望得到贵公司的垂青，仔细认真评估一下我们的商业计划书，期待贵公司的回复，谢谢！").append(line);
        text.append(head).append(line);
        text.append(head).append("联系人：王经理").append(line);
        text.append(head).append("电话：13693246662").append(line);
        helper.setText(text.toString(),true);

        //附件内容
        FileSystemResource file = new FileSystemResource(new File(attachFile));
        helper.addAttachment("商业计划书.pdf",file);

        mailSender.send(message);

        logger.info("{} 邮件发送成功",receiver);

    }

    @Override
    public List<String> findReceivers() throws IOException {

        ExcelUtils excelUtils = new ExcelUtils(new File(receiverFile));
        //String date = LocalDate.now().toString().replace("-","");
        List<String> list = excelUtils.readBySheetIndex(0);

        return list;
    }
}
