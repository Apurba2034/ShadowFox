package InventoryManagementSystem;

import javafx.beans.property.*;

public class Product {

    private final StringProperty barcode = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();

    public Product(String barcode, String name, int quantity, double price) {
        this.barcode.set(barcode);
        this.name.set(name);
        this.quantity.set(quantity);
        this.price.set(price);
    }

    public StringProperty barcodeProperty() { return barcode; }
    public StringProperty nameProperty() { return name; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty priceProperty() { return price; }

    public String getBarcode() { return barcode.get(); }
    public String getName() { return name.get(); }
    public int getQuantity() { return quantity.get(); }
    public double getPrice() { return price.get(); }

    public void setQuantity(int q) { quantity.set(q); }
    public void setPrice(double p) { price.set(p); }
}
