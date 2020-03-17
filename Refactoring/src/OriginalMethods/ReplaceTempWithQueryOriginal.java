package OriginalMethods;

public class ReplaceTempWithQueryOriginal {
    int _quantity;
    int _itemPrice;
    
    double getPrice() {
        int basePrice = _quantity * _itemPrice;
        double discountFactor;
        if (basePrice > 1000) discountFactor = 0.95;
        else discountFactor = 0.98;
        return basePrice * discountFactor;
        }
}
