package com.csidigital.rh.management.service;

import com.csidigital.rh.dao.entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
