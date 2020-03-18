package refactoredMethods;

public class ReplaceMethodWithMethodObjectRefactored {
   
    class Account{
        
    int gamma (int inputVal, int quantity, int yearToDate) {
        return new Gamma(this, inputVal, quantity, yearToDate).compute();
    }
    
    int delta () {
        return 2;
    }
}
    
    class Gamma{
        
        private final Account _account;
        private final int _inputVal;
        private final int _quantity;
        private final int _yearToDate;
        private int _importantValue1;
        private int _importantValue2;
        private int _importantValue3;
    
        public Gamma(Account account, int inputVal, int quantity, int yearToDate) {
            super();
            _account = account;
            _inputVal = inputVal;
            _quantity = quantity;
            _yearToDate = yearToDate;
          }
        
        public int compute() {
            _importantValue1 = (_inputVal * _quantity) + _account.delta();
            _importantValue2 = (_inputVal * _yearToDate) + 100;
            _importantValue2 = importantThing();
            _importantValue3 = _importantValue2 * 7;
            // and so on.
            return _importantValue3 - 2 * _importantValue1;
        }

        public int importantThing() {
            if ((_yearToDate - _importantValue1) > 100)
                _importantValue2 -= 20;
            return _importantValue2;
        }
    
    }
    
}