package refactoredMethods;

import java.util.Date;
import java.util.Vector;

public class PullUpMethodRefactored {
    abstract class Customer {
        protected Date lastBillDate;
        protected Vector _bills = new Vector();
        
        void addBill(Date date, double amount) {
            _bills.add(amount);
            lastBillDate = date;
        }
        
        void createBill (Date date) {    
            double chargeAmount = chargeFor(lastBillDate, date);
            addBill (date, chargeAmount);
            }
        
        abstract double chargeFor(Date start, Date end);
    }
    
    class RegularCustomer extends Customer {  
        @Override
        double chargeFor(Date start, Date end) {
            return (end.getDay()-start.getDay()) * 1.2;
        }   
    }
    

    class PreferredCustomer extends Customer {
        @Override
        double chargeFor(Date start, Date end) {
            return (end.getDay()-start.getDay()) * 1;
        }
    }
}




