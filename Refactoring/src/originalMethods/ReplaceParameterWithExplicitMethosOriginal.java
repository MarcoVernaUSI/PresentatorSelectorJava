package originalMethods;

public class ReplaceParameterWithExplicitMethosOriginal {
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
    
    static Employee create(int type) {
        switch (type) {
        case ENGINEER:
            return new Engineer();
        case SALESMAN:
            return new Salesman();
        case MANAGER:
            return new Manager();
        default:
            throw new IllegalArgumentException("Incorrect type code value");
        }
    }


    static public class Employee {

    }
    
    
    static class Engineer extends Employee {
    
    }
    
    static class Manager extends Employee {
  
    }
    
    static class Salesman extends Employee {
   
     }

    
    
    
}
