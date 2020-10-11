package com.epam.university.java.core.task023;
/*
 * Completed by Laptev Egor 06.10.2020
 * */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task023Impl implements Task023 {
    @Override
    public String extract(String phoneString) {
        if (phoneString == null || phoneString.length() < 11) {
            throw new IllegalArgumentException();
        }
        Pattern p = Pattern
                .compile("^[+][7][-(]|^[8][(]|^[+][7][ ][(]|^[+][7][ ]|[+][7]|^[8][-]|^[8]");
        Matcher m = p.matcher(phoneString);
        int offset = 0;
        while (m.find()) {
            offset += m.end();
        }
        return phoneString.substring(offset, offset + 3);
    }
}
