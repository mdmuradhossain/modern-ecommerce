package io.murad.modern.ecommerce.database.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NotificationEmail {

    private String subject;
    private String recipient;
    private String body;
}
