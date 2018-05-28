package pl.kuba565.device;

import pl.kuba565.model.Product;

import java.math.BigDecimal;

public class ConsoleLoggingDisplay implements Display {
    private String output = "";

    public ConsoleLoggingDisplay() {
    }

    @Override
    public void logProductNotFound() {
        System.out.println("DISPLAY: Product not found.");
        output += "DISPLAY: Product not found.";
    }

    @Override
    public void logInvalidBarCode() {
        System.out.println("DISPLAY: Invalid bar code.");
        output += "DISPLAY: Invalid bar code.";
    }

    @Override
    public void logScannedProductNameAndPrice(Product scannedProduct) {
        BigDecimal price = scannedProduct.getPrice();
        String name = scannedProduct.getName();
        System.out.println("DISPLAY: Scanned: " + name + " price: " + price);
        output += "DISPLAY: Scanned: " + name + " price: " + price;
    }


    @Override
    public void logTotalSum(BigDecimal totalSum) {
        System.out.println("DISPLAY: total sum: " + totalSum);
        output += "DISPLAY: total sum: " + totalSum;

    }

    @Override
    public String getMessage() {
        return output;
    }
}
