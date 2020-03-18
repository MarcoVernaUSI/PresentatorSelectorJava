package OriginalMethods;

public class PreserveWholeObjectRefactored {
    class Room{
        boolean withinPlan(HeatingPlan plan) {
            return plan.withinRange(daysTempRange());
        }
        
        public TempRange daysTempRange() {
            return new TempRange();
        }
        
    }
    
    class HeatingPlan{
        boolean withinRange (TempRange roomRange) {
            return (_range.includes(roomRange));
        }
        private TempRange _range;
    }
    
    
    class TempRange{
        private int _low;
        private int _high;
        
        boolean includes (TempRange arg) {
            return arg.getLow() >= this.getLow() && arg.getHigh() <=
            this.getHigh();
        }
        
        public int getHigh() {
            return _high;
        }
        public int getLow() {
            return _low;
        }
        }
}
