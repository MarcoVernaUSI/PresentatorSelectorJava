package originalMethods;

public class RemoveSettingMethodOriginal {
    class Account {
        private String _id;
        
        Account (String id) {
            setId(id);
        }
        
        void setId (String arg) {
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
            setId(id);
            _interestRate = rate;
        }
    
        double getInterestRate() {
            return _interestRate;
        }
    }
}
