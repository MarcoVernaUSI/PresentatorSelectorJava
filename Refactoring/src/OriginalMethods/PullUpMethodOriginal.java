package OriginalMethods;

import java.util.Date;
import java.util.Vector;

public class PullUpMethodOriginal {
    class Customer {
        protected Date lastBillDate;
        protected Vector _bills = new Vector();
        
        void addBill(Date date, double amount) {
            _bills.add(amount);
            lastBillDate = date;
            
        }  
    }
    
    class RegularCustomer extends Customer {
        void createBill (Date date) {    
            double chargeAmount = chargeFor(lastBillDate, date);
            addBill (date, chargeAmount);
            }
        
        double chargeFor(Date start, Date end) {
            return (end.getDay()-start.getDay()) * 1.2;
        }
        
    }
    

    class PreferredCustomer extends Customer {
        void createBill (Date date) {    
            double chargeAmount = chargeFor(lastBillDate, date);
            addBill (date, chargeAmount);
            }
        
        double chargeFor(Date start, Date end) {
            return (end.getDay()-start.getDay()) * 1;
        }
    }
    

}




