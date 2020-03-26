package refactoredMethods;

import java.util.Vector;

public class ReplaceInheritanceWithDelegationRefactored {
    class MyStack {
        private final Vector _vector = new Vector();
        
        public void push(Object element) {
            _vector.insertElementAt(element,0);
            }
        
        public Object pop() {
            Object result = _vector.firstElement();
            _vector.removeElementAt(0);
            return result;
            }
        
        public int size() {
            return _vector.size();
            }
            public boolean isEmpty() {
            return _vector.isEmpty();
            }
    }
}
