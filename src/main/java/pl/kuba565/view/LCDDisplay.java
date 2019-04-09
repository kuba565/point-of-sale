package pl.kuba565.view;

import pl.kuba565.model.Product;

import java.math.BigDecimal;

public class LCDDisplay implements Display {
    private StringBuilder outputSB = new StringBuilder();

    public LCDDisplay() {
    }

    @Override
    public void logProductNotFound() {

        outputSB.append("DISPLAY: Product not found.")
                .append(System.lineSeparator());
    }

    @Override
    public void logInvalidBarCode() {
        outputSB.append("DISPLAY: Invalid bar code.")
                .append(System.lineSeparator());
    }

    @Override
    public void logScannedProductNameAndPrice(Product scannedProduct) {
        BigDecimal price = scannedProduct.getPrice();
        String name = scannedProduct.getName();
        outputSB.append("DISPLAY: Scanned: " + name + " price: " + price)
                .append(System.lineSeparator());
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

    private void printOnScreen(String msg) {
        System.out.println(msg);
    }
}