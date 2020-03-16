package mainPackage;

public class SimpleQueue implements Queue {
    private final Object[] _queue;
    private final int _maxSize;
    private int _front;
    private int _rear;
    private int _currentSize;
    
    
    
    
    public SimpleQueue(int size) {
        _queue = new Object[size];
        _maxSize = size;
        _front = 0;
        _rear = -1;
        _currentSize = 0;
    }
    
    @Override
    public Object peek() {
        return _queue[_front];
    }
    
    @Override
    public boolean isFull() {
        if (_currentSize == _maxSize) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public boolean isEmpty() {
        if (_currentSize==0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void enqueue(Object data) {
        if (isFull()) {
            System.out.println("Queue is full!");
        } else {
            _rear++;
            if (_rear == _maxSize) {
                _rear = 0;                
            }
            _queue[_rear]= data;
            _currentSize++;
        }
    }
    
    @Override
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            _front++;
            if (_front == _maxSize) {
                _front = 0;                
            }
            _currentSize--;
        }
    }
}
