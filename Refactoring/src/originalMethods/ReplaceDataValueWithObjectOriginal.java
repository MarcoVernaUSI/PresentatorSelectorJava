package originalMethods;

import java.util.Collection;
import java.util.Iterator;

public class ReplaceDataValueWithObjectOriginal {
    class Order{
        
        public Order(String customer) {
            _customer = customer;      
        }
         
        public String getCustomerName() {
            return _customer;
        }
       
        public void setCustomer(String arg) {
            _customer = arg;
        }
        
        private String _customer;
  
        
        public int numberOfOrdersFor(Collection orders, String customer) {
             int result = 0;
             Iterator iter = orders.iterator();
             while (iter.hasNext()) {
             Order each = (Order) iter.next();
             if (each.getCustomerName().equals(customer)) result++;
             }
             return result;
             }
    }
}
