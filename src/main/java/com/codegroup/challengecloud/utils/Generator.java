package com.codegroup.challengecloud.utils;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Max on 24.01.2015.
 */
public class Generator {

    public static String generateId(){
        UUID uuid = UUID.randomUUID();
        String strId = uuid.toString();
        String[] idFields = strId.split("-");
        return idFields[1]+idFields[4];
    }
    
    public static String generateHashedPass(String password) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
    }

    public static void main(String[] args) {
        System.out.println(generateId());
        System.out.println(generateHashedPass("123"));
    }
}
