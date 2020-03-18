package OriginalMethods;

public class PreserveWholeObjectOriginal {
    class Room{
        boolean withinPlan(HeatingPlan plan) {
            int low = daysTempRange().getLow();
            int high = daysTempRange().getHigh();
            return plan.withinRange(low, high);
        }
        
        public TempRange daysTempRange() {
            return new TempRange();
        }
        
    }
    
    class HeatingPlan{
        boolean withinRange (int low, int high) {
            return (low >= _range.getLow() && high <= _range.getHigh());
        }
        private TempRange _range;
    }
    
    
    class TempRange{
        private int _low;
        private int _high;
        
        public int getHigh() {
            return _high;
        }
        public int getLow() {
            return _low;
        }
        }
}
