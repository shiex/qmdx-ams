package com.qmdx.ams.service;

import com.qmdx.ams.entity.Company;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {

    /**
     * Send a text message
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content) throws MessagingException;

    /**
     * Send a html message
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;
}
