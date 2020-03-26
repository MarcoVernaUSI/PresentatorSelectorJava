package refactoredMethods;

public class IntroduceNullObjectRefactored {
    
    class Site {
        Customer getCustomer() {
            return (_customer .isNull()) ? Customer.newNull(): _customer;
        }
        
        Customer _customer;
    }
    
    
    static class Customer{
        String _name;
        BillingPlan _plan;
        PaymentHistory _history;
        
        public static Customer newNull() {
            return new NullCustomer();
        }
        
        public boolean isNull() {return false;}
        
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
    
    static class NullCustomer extends Customer{
        @Override
        public String getName() {return "occupant";}
        
        @Override
        public boolean isNull() {return true;}
    }
    
    
    
    class PaymentHistory{
        int getWeeksDelinquentInLastYear() {return 2;};
    }
    
    class BillingPlan{}
}
