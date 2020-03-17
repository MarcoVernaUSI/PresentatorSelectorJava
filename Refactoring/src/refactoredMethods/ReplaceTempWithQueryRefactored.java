package refactoredMethods;

public class ReplaceTempWithQueryRefactored {
    int _quantity;
    int _itemPrice;
    
    double getPrice() {
        return basePrice()  * discountFactor();
    }

    public double discountFactor() {
        if (basePrice() > 1000)  return 0.95;
        else return 0.98;
    }

    public int basePrice() {
        return _quantity * _itemPrice;
    }
}
