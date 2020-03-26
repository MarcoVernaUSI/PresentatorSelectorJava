package originalMethods;

import java.util.Date;

public class DecomposeConditionalOriginal {

    private final static Date SUMMER_START= new Date();
    private final static Date SUMMER_END= new Date();
    private int _winterRate;
    private int _winterServiceCharge;
    private int _summerRate;
    
    
    public int veryCoolMethod(Date date, int quantity) {
        int charge;
        
        if (date.before (SUMMER_START) || date.after(SUMMER_END))
            charge = quantity * _winterRate + _winterServiceCharge;
            else charge = quantity * _summerRate;
        
        return charge;
    }
}
