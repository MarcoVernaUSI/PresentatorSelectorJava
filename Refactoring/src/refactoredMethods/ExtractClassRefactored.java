package refactoredMethods;

public class ExtractClassRefactored {
    
class Person {
    private String _name;
    private final TelephoneNumber _officeTelephone = new TelephoneNumber();

    public String getName() {
        return _name;
    }
    
    public String getTelephoneNumber() {
        return  _officeTelephone.getTelephoneNumber();
    }
   
    TelephoneNumber getOfficeTelephone() {
        return _officeTelephone;
    }
}


class TelephoneNumber{
    private String _areaCode;
    private String _number;
    
    String getAreaCode() {
        return _areaCode;
    }

    public String getTelephoneNumber() {
        return ("("+ _areaCode + ")" + _number);
    }

    void setAreaCode(String arg) {
        _areaCode = arg;
    }

    public String getNumber() {
        return _number;
    }

    public void setNumber(String number) {
        _number = number;
    }
}
}