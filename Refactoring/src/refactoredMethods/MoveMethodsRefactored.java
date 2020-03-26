package refactoredMethods;

public class MoveMethodsRefactored {
    class Account{
        
        double bankCharge() {
            double result = 4.5;
            if (_daysOverdrawn > 0) result += _type.overdraftCharge(_daysOverdrawn);
            return result;
        }
   
        private AccountType _type;
        private int _daysOverdrawn;
    
    }
    
    class AccountType{
        private boolean _premium;
        
        double overdraftCharge(int daysOverdrawn) {
            if (_premium) {
                double result = 10;
                if (daysOverdrawn > 7) result += (daysOverdrawn - 7) * 0.85;
                return result;
            }
            else return daysOverdrawn * 1.75;
            
        }
        
        public boolean isPremium() {
            return _premium;
        }
    }
    
    

}
