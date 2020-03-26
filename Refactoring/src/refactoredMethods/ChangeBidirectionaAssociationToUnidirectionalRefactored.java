package refactoredMethods;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class ChangeBidirectionaAssociationToUnidirectionalRefactored {
    class Order{ 
        
        double getDiscountedPrice(Customer customer) {
            return getGrossPrice() * (1 - customer.getDiscount());
            }
        
        private int getGrossPrice() {
            return 0;
        }
    }
    
    
    class Customer{
    
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
            return order.getDiscountedPrice(this);
            }
    }
}
