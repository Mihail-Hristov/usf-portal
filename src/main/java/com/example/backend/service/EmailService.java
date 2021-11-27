package com.example.backend.service;

import com.example.backend.util.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(AbstractEmailContext email) throws MessagingException;
}
