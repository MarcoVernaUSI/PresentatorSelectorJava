package refactoredMethods;

public class MoveFieldRefactored {
    class Account{
        
        double interestForAmount_days (double amount, int days) {
            return _type.getInterestRate() * amount * days / 365;
            }
   
       private AccountType _type;
    
    }
    
    class AccountType{
        private double _interestRate;

        public double getInterestRate() {
            return _interestRate;
        }
   
    }
    
    

}
