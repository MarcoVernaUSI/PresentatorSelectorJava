package smell;

import java.io.PrintWriter;

class LineItem {
    private final int _productId;
    private final int _ImageID;
    private final int _qty;
    private final int _Unitprice;
 
    public LineItem(int prodID, int ImageID, int inQty, int unitPrice) {
        _productId = prodID;
        _ImageID = ImageID;
        _qty = inQty;
        _Unitprice = unitPrice;
    }
    
    public int getLineItemTotal() {
        return _Unitprice * _qty;
    }

    public void writeItem(PrintWriter pw) {
        pw.println("Begin Line Item");
        pw.println("Product = " + _productId);
        pw.println("Image = " + _ImageID);
        pw.println("Quantity = " + _qty);
        pw.println("Total = " + _Unitprice);
        pw.println("End Line Item");
    }
    
}
