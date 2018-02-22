package com.jm.newvista.util;

import java.util.ArrayList;
import java.util.List;

public class SeatIdUtil {
    public static String generate(List<Integer> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer id : ids) {
            stringBuffer.append(id + ",");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static List<Integer> parse(String idsString) {
        String[] idsArray = idsString.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String id : idsArray) {
            ids.add(Integer.parseInt(id));
        }
        return ids;
    }

//    public static void main(String[] args) {
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(3);
//        ids.add(16);
//        String s = generate(ids);
//        System.out.println("Generate: " + s);
//        System.out.println("Parse: " + parse(s));
//    }
}
