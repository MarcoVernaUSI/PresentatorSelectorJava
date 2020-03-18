package refactoredMethods;

import java.util.Collection;
import java.util.Iterator;

public class ReplaceDataValueWithObjectRefactored {
    class Order{
        
        public Order(String customerName) {
            _customer = new Customer(customerName);      
        }
         
        public String getCustomerName() {
            return _customer.getName();
        }
       
        public void setCustomer(String customerName) {
            _customer = new Customer(customerName);
        }
        
        private Customer _customer;
  
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
    
    class Customer {
        public Customer (String name) {
            _name = name;
        }
        
        public String getName() {
            return _name;
        }
        
        private final String _name;
        }
}
