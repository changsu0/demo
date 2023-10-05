package com.example.demo.test;


import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static java.awt.SystemColor.text;

public class MhtData {
    public static void main(String[] args) throws IOException, MessagingException {
        getDataMht("C:/test.mht");
    }

    public static String getDataMht(String filePath) throws IOException, MessagingException, MessagingException {
        // mht 파일 경로
//        String filePath = "path/to/file.mht";

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        File file = new File(filePath);
        FileInputStream is = new FileInputStream(filePath);

        InputStreamReader reader = new InputStreamReader(is, "UTF-8");
        MimeMessage message = new MimeMessage(session, is);
        Object content = message.getContent();

        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;

            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String con = bodyPart.getContent().toString();

//                System.out.println("Type: " + bodyPart.getContentType());
                System.out.println("Content: " + bodyPart.getContent());
            }
        }
        return filePath;
    }
}