package com.example.qrmanagementservice.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;


@Slf4j
public class Md5HashGenerator {
    public static String generateHashFor(String s) {
        return DigestUtils.md5Hex(s).toUpperCase();
    }
}
