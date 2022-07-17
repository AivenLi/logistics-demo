package com.aiven.logisticsdemo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  : AivenLi
 * @date    : 2022/7/14 21:39
 * */
public class PhoneNumberUtil {

    public static List<PhoneNumberUtil.PhoneNumberIndex> getAllPhoneNumberIndex(String str) {
        List<PhoneNumberUtil.PhoneNumberIndex> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                int start = i++;
                int hasLand = 0;
                while (i < chars.length && ((chars[i] >= '0' && chars[i] <= '9') || (chars[i] == '-'))) {
                    if (chars[i] == '-') {
                        hasLand++;
                        if (hasLand > 1) {
                            break;
                        }
                    }
                    ++i;
                }
                if (hasLand <= 1 && (i - start) >= 5) {
                    list.add(new PhoneNumberUtil.PhoneNumberIndex(start, i));
                }
            }
        }
        return list;
    }

    public static class PhoneNumberIndex {
        public int startIndex;
        public int endIndex;

        public PhoneNumberIndex(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}