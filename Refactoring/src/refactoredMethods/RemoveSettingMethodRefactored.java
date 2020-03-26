package refactoredMethods;

public class RemoveSettingMethodRefactored {
    class Account {
        private String _id;
        
        Account (String id) {
            initializeId(id);
        }
        
        void initializeId (String arg) {
            _id = "ZZ" + arg;
            }
        
        String getId() {
            return _id;
        }
    }
    
    class InterestAccount extends Account{
        private final double _interestRate;
    
        InterestAccount (String id, double rate) {
            super(id);
            initializeId(id);
            _interestRate = rate;
        }
    
        double getInterestRate() {
            return _interestRate;
        }
    }
}
