package refactoredMethods;

import java.util.Date;

public class DecomposeConditionalRefactored {

    private final static Date SUMMER_START= new Date();
    private final static Date SUMMER_END= new Date();
    private int _winterRate;
    private int _winterServiceCharge;
    private int _summerRate;
    
    
    public int veryCoolMethod(Date date, int quantity) {
        int charge;
        
        if (notSummer(date))
            charge = winterCharge(quantity);
            else charge = summerCharge(quantity);
        
        return charge;
    }


    public int summerCharge(int quantity) {
        return quantity * _summerRate;
    }


    public int winterCharge(int quantity) {
        return quantity * _winterRate + _winterServiceCharge;
    }


    public boolean notSummer(Date date) {
        return date.before (SUMMER_START) || date.after(SUMMER_END);
    }
}
