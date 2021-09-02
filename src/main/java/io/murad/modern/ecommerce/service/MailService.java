package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.NotificationEmail;
import io.murad.modern.ecommerce.exception.ModernEcommerceException;
import io.murad.modern.ecommerce.util.MailContentBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private static final String FROM_EMAIL = "mdmuradhossain@gmail.com";
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator mimeMessagePreparator = (mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(FROM_EMAIL);
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setText(notificationEmail.getBody());
        });
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Activation Mail Sent.");
        }catch(MailException mailException){
            throw new ModernEcommerceException(""+notificationEmail.getRecipient());
        }


    }
}
