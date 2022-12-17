package Y1F.Other;

import java.text.DecimalFormat;
import java.util.Date;

public class DecimalPractice {

    public static void main(String[] args) {

        Date date = new Date();
        System.out.printf("Today %tA %<tB  %<te,  %<tY is pretty cold in Canada %n", date); //%tA %<tB  %<te,  %<tY

        DecimalFormat fmt = new DecimalFormat("0.##");

        float potatoes = 7.565f;
        System.out.println(fmt.format(potatoes));

    }
    
}
