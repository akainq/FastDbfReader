/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.core;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Aleksey
 */
public class Utils {
    
    static final int NR_OF_DIGITS_IN_YEAR = 4;
    
       public static short changeEndianness(short shortValue)
    {
        boolean isNegative = false;
        short s = shortValue;

        if (s < 0)
        {
            isNegative = true;
            s &= 0x7fff;
        }

        int first = s >>> 8;

        if (isNegative)
        {
            first |= 0x80;
        }

        int second = s & 0x00ff;

        return (short) ((second << 8) + first);
    }
   
     public static int changeEndianness(final int integerValue)
    {
        boolean isNegative = false;
        int i = integerValue;

        if (i < 0)
        {
            isNegative = true;
            i &= 0x7fffffff;
        }

        int first = i >>> 24;

        if (isNegative)
        {
            first |= 0x80;
        }

        i = integerValue & 0x00ff0000;

        int second = i >>> 16;

        i = integerValue & 0x0000ff00;

        int third = i >>> 8;

        int fourth = integerValue & 0x000000ff;

        return (fourth << 24) + (third << 16) + (second << 8) + first;
    } 
     
       public static Date createDate(int year, int month, int day)
    {
                
        
        final Calendar cal = Calendar.getInstance();

        if (Integer.toString(year).length() > NR_OF_DIGITS_IN_YEAR)
        {
            throw new IllegalArgumentException("Year more than" + NR_OF_DIGITS_IN_YEAR + " digits long");
        }

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}
