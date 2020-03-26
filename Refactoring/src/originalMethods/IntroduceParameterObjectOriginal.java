package originalMethods;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class IntroduceParameterObjectOriginal {
    
    class Entry{
        Entry (double value, Date chargeDate) {
            _value = value;
            _chargeDate = chargeDate;
        }
    
        Date getDate(){
            return _chargeDate;
        }
    
        double getValue(){
            return _value;
        }
    
        private final Date _chargeDate;
        private final double _value;
    }
    
    class Account{
    
        double getFlowBetween (Date start, Date end) {
            double result = 0;
            Enumeration e = _entries.elements();
            while (e.hasMoreElements()) {
                Entry each = (Entry) e.nextElement();
                if (each.getDate().equals(start) ||
                    each.getDate().equals(end) ||
                    (each.getDate().after(start) &&
                        each.getDate().before(end)))
                {
                    result += each.getValue();
                }
            }
            return result;
        }
        private final Vector _entries = new Vector();
     }
    
    class clientCode {
        Account anAccount;
        
        public double getFlow(Date startDate, Date endDate) {
            double flow = anAccount.getFlowBetween(startDate, endDate);        
            return flow;
        }
    }
        
}
