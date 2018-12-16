package com.baolishang.springboot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hello on 2016/9/22.
 */
public class ParameterUtil {

    public static int intParam(String input){
        if (input == null) return 1 ;
        else{
            Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);
            if (mer.find()) return Integer.parseInt(input);
            else return 1 ;
        }
    }

}
