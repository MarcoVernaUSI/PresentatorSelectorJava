package refactoredMethods;

public class ReplaceParameterWithExplicitMethosRefactored {
    


    public static class Employee {
        static Employee createEngineer() {
            return new Engineer();
            }
            static Employee createSalesman() {
            return new Salesman();
            }
            static Employee createManager() {
            return new Manager();
            }
    }
    
    
    static class Engineer extends Employee {
    
    }
    
    static class Manager extends Employee {
  
    }
    
    static class Salesman extends Employee {
   
     }
}
    
