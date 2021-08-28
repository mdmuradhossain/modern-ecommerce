package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.NotificationEmail;
import io.murad.modern.ecommerce.util.MailContentBuilder;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private static final String FROM_EMAIL = "mdmuradhossain@gmail.com";
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

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
        }catch(MailException mailException){
            throw new ModernEcommerceException(""+notificationEmail.getRecipient());
        }


    }
}
