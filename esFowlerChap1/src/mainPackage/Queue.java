package mainPackage;

public interface Queue {  
    
    public abstract Object peek();
    
    public abstract boolean isFull();
    
    public abstract boolean isEmpty();
    
    public abstract void enqueue(Object data);
    
    public abstract void dequeue();

}
