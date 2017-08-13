package com.zy.common.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/13.
 */
public class RegularExpression {
    private static final Pattern pattern = Pattern.compile("zystudio-registry-center");
    public static void main(String argv[]){
        String str = "afddaszystudio-registry-center/pom.xml'asdf";

        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher);
            System.out.println(matcher.group());
        }
    }
}
