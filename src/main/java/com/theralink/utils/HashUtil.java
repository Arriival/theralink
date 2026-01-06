package com.theralink.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashUtil {
    public static String hashPassword(String password) {
        String hash1 = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return Hashing.sha512().hashString(hash1, StandardCharsets.UTF_8).toString();
    }

    public static void main(String[] args) {
        String password = "admin";
        System.out.println(hashPassword(password));
        System.out.println(hashPassword("Me@12345"));
        System.out.println(hashPassword("123456"));
    }
}
