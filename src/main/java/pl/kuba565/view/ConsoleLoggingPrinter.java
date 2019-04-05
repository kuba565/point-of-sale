package pl.kuba565.view;

import pl.kuba565.model.Product;
import pl.kuba565.model.Receipt;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleLoggingPrinter implements Printer {
    private String output = "";

    public String printReceipt(Receipt receipt) {
        List<Product> scannedProductList = receipt.getProductList();
        BigDecimal totalSum = receipt.getTotalSum();
        StringBuilder sB = new StringBuilder(System.lineSeparator());

        sB.append("RECEIPT:")
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator());

        AtomicInteger productIndex = new AtomicInteger(1);
        scannedProductList.forEach(currentProduct -> {
            sB.append(productIndex.get())
                    .append(". name: ")
                    .append(currentProduct.getName())
                    .append(" price: ")
                    .append(currentProduct.getPrice())
                    .append(System.lineSeparator());
            productIndex.getAndIncrement();
        });

        sB.append("-----------")
                .append(System.lineSeparator())
                .append("total receipt price: ")
                .append(totalSum)
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator());

        output += sB.toString();
        System.out.println(output);
        return output;
    }

    @Override
    public String getOutput() {
        return output;
    }
}
