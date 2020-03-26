package originalMethods;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class ChangeBidirectionaAssociationToUnidirectionalOriginal {
    class Order{ 
        Customer getCustomer() {
            return _customer;
        }
    
        void setCustomer (Customer arg) {
            if (_customer != null) _customer.friendOrders().remove(this);
                _customer = arg;
            if (_customer != null) _customer.friendOrders().add(this);
        }
        
        double getDiscountedPrice() {
            return getGrossPrice() * (1 - _customer.getDiscount());
        }
        
        private int getGrossPrice() {
            return 0;
        }

        private Customer _customer;
    }
    
    
    class Customer{
        void addOrder(Order arg) {
            arg.setCustomer(this);
        }
    
        public int getDiscount() {
            return 0;
        }

        private final Set _orders = new HashSet();
    
        Set friendOrders() {
            /** should only be used by Order */
            return _orders;
        }
        
        double getPriceFor(Order order) {
            assertTrue(_orders.contains(order)); 
            return order.getDiscountedPrice();
        }
    }
}
