package com.example.platform.utils;

import java.util.Random;

public class CarIdUtil {
    public static String genCarId(){
        Random random1=new Random();

        StringBuffer stringBuffer=new StringBuffer();
        long result;
        for(int i=0;i<8;i++)
        {
            int number=random1.nextInt(2);
            switch (number){
                case 0:
                    result=Math.round(Math.random()*25+97);
                    stringBuffer.append(String.valueOf((char)result));
                    break;
                case 1:
                    stringBuffer.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return String.valueOf(stringBuffer);
    }

}
