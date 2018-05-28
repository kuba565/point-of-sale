package pl.kuba565.device;

import pl.kuba565.model.Product;
import pl.kuba565.model.Receipt;

import java.math.BigDecimal;
import java.util.List;

public class ConsoleLoggingPrinter implements Printer {
    private String output = "";

    public void printReceipt(Receipt receipt) {
        List<Product> scannedProductList = receipt.getScannedProductList();
        BigDecimal totalSum = receipt.getTotalSum();
        StringBuilder sB = new StringBuilder(System.lineSeparator());

        sB.append("RECEIPT:")
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator());

        for (int i = 0; i < scannedProductList.size(); i++) {
            Product currentProduct = scannedProductList.get(i);
            Long positionNumber = (long) (i + 1);
            sB.append(positionNumber)
                    .append(". name: ")
                    .append(currentProduct.getName())
                    .append(" price: ")
                    .append(currentProduct.getPrice())
                    .append(System.lineSeparator());
        }
        sB.append("-----------")
                .append(System.lineSeparator())
                .append("total receipt price: ")
                .append(totalSum)
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator());
        String result = sB.toString();

        output += result;

        System.out.println(result);
    }

    public String getOutput() {
        return output;
    }
}
