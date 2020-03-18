package refactoredMethods;  

public class ReplaceArrayWithAnObjectRefactored {
    Performance row = new Performance();
    
    public String method() {
        row.setName("Liverpool");
        row.setWins("15");
        String name = row.getName();
        int wins = row.getWins(); 
        
        return name +""+ wins;
    }
    
    
    class Performance{
        private String _name;
        private String _wins;
        
        public String getName() {
            return _name;
        }
        
        public void setName(String arg) {
            _name = arg;
        }
            
        public int getWins() {
            return Integer.parseInt(_wins);
        }
        
        public void setWins(String arg) {
            _wins = arg;
        }
        
    }
}
