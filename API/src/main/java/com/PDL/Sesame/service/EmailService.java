package com.PDL.Sesame.service;

public interface EmailService {

    void sendEmail(String subject, String body, String[] recipients);
}
