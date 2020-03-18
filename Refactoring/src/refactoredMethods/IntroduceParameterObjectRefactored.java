package refactoredMethods;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class IntroduceParameterObjectRefactored {
    
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
    
        double getFlowBetween (DateRange range) {
            double result = 0;
            Enumeration e = _entries.elements();
            while (e.hasMoreElements()) {
                Entry each = (Entry) e.nextElement();
                if (range.includes(each.getDate())){
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
            double flow = anAccount.getFlowBetween(new DateRange(startDate, endDate));        
            return flow;
        }
    }
    
    class DateRange {
        private final Date _start;
        private final Date _end;
        
        public DateRange(Date start, Date end) {
            _start = start;
            _end = end;
        }
        Date getStart() {
            return _start;
            }
        Date getEnd() {
            return _end;
            }
        
        boolean includes (Date arg) {
            return (arg.equals(_start))||
                arg.equals(_end)||
                (arg.after(_start) && arg.before(_end));
        }
    }
    
        
}
