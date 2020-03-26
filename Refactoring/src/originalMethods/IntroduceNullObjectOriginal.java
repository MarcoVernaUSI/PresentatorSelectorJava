package originalMethods;

public class IntroduceNullObjectOriginal {
    
    class Site {
        Customer getCustomer() {
            return _customer;
        }
        
        Customer _customer;
    }
    
    
    class Customer{
        
        String _name;
        BillingPlan _plan;
        PaymentHistory _history;
        
        public String getName() {
            return _name;
        }
        
        public BillingPlan getPlan(){
            return _plan;
        }
        
        public PaymentHistory getHistory(){
            return _history;
        }
    }
    
    class PaymentHistory{
        int getWeeksDelinquentInLastYear() {return 2;};
    }
    
    class BillingPlan{}
}
