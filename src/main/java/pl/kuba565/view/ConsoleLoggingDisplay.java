package pl.kuba565.view;

import pl.kuba565.model.Product;

import java.math.BigDecimal;

public class ConsoleLoggingDisplay implements Display {
    private StringBuilder outputSB = new StringBuilder();

    public ConsoleLoggingDisplay() {
    }

    @Override
    public void logProductNotFound() {
        outputSB.append("DISPLAY: Product not found.");
    }

    @Override
    public void logInvalidBarCode() {
        outputSB.append("DISPLAY: Invalid bar code.");
    }

    @Override
    public void logScannedProductNameAndPrice(Product scannedProduct) {
        BigDecimal price = scannedProduct.getPrice();
        String name = scannedProduct.getName();
        outputSB.append("DISPLAY: Scanned: " + name + " price: " + price);
    }


    @Override
    public void logTotalSum(BigDecimal totalSum) {
        outputSB.append("DISPLAY: total sum: " + totalSum);
    }

    @Override
    public String flush() {
        String msg = outputSB.toString();
        printOnScreen(msg);
        return msg;
    }

    //TODO: FIX DISPLAY OUTPUT CONCATENATION!
    private void printOnScreen(String msg) {
        System.out.println("DISPLAY: " + msg);
    }
}
