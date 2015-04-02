package com.codegroup.challengecloud.utils;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Max on 24.01.2015.
 */
public class Generator {

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String strId = uuid.toString();
        String[] idFields = strId.split("-");
        return idFields[1] + idFields[4];
    }

    public static String generateHashedPass(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    /*By Yefim*/
    public static String generateRandomPass() {
        int DEFAULT_LENGTH = 15;
        String ABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer buffer = new StringBuffer();

        int charactersLength = ABC.length();

        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(ABC.charAt((int) index));
        }
        return generateHashedPass(buffer.toString());
    }

    public static void main(String[] args) {
        System.out.println(generateId());
        System.out.println(generateHashedPass("123"));
        System.out.println(generateRandomPass());
    }
}
