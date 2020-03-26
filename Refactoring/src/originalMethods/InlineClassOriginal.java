package originalMethods;

public class InlineClassOriginal {

    class Person{
       
        public String getName() {
            return _name;
        }
    
        public String getTelephoneNumber(){
            return _officeTelephone.getTelephoneNumber();
        }
    
        TelephoneNumber getOfficeTelephone() {
            return _officeTelephone;
        }
    
        private String _name;
        private final TelephoneNumber _officeTelephone = new TelephoneNumber();    
    }
        
    class TelephoneNumber{
        
        private String _areaCode;
        private String _number;

        public String getTelephoneNumber() {
            return ("(" + _areaCode + ") " + _number);
        }
    
        String getAreaCode() {
            return _areaCode;
        }
    
        void setAreaCode(String arg) {
            _areaCode = arg;
        }
    
        String getNumber() {
            return _number;
        }
    
        void setNumber(String arg) {
            _number = arg;
        }
    }
}
