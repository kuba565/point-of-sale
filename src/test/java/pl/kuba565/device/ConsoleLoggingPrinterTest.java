package pl.kuba565.device;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.model.Product;
import pl.kuba565.model.Receipt;
import pl.kuba565.model.StandardProduct;
import pl.kuba565.model.StandardReceipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleLoggingPrinterTest {

    @Test
    public void shouldPrintReceipt() {
        ConsoleLoggingPrinter consoleLoggingPrinter = new ConsoleLoggingPrinter();
        Product product1 = new StandardProduct("test1", new BigDecimal("1"), 1L);
        Product product2 = new StandardProduct("test2", new BigDecimal("2"), 2L);
        List<Product> products = Arrays.asList(product1, product2);
        BigDecimal totalSum = new BigDecimal(0);

        if (products.size() > 0) {
            for (Product currentProduct : products) {
                totalSum = totalSum.add(currentProduct.getPrice());
            }
        }

        Receipt receipt = new StandardReceipt(products, totalSum);

        consoleLoggingPrinter.printReceipt(receipt);

        //verify
        final String expected = "\n" +
                "RECEIPT:\n" +
                "-----------\n" +
                "1. name: test1 price: 1\n" +
                "2. name: test2 price: 2\n" +
                "-----------\n" +
                "total receipt price: 3\n" +
                "-----------\n";

        Assert.assertEquals(expected, consoleLoggingPrinter.getOutput());
    }

    @Test
    public void shouldPrintEmptyReceipt() {
        ConsoleLoggingPrinter consoleLoggingPrinter = new ConsoleLoggingPrinter();

        List<Product> products = new ArrayList<>();
        BigDecimal totalSum = new BigDecimal(0);


        Receipt receipt = new StandardReceipt(products, totalSum);

        consoleLoggingPrinter.printReceipt(receipt);

        //verify
        final String expected = "\n" +
                "RECEIPT:\n" +
                "-----------\n" +
                "-----------\n" +
                "total receipt price: 0\n" +
                "-----------\n";

        Assert.assertEquals(expected, consoleLoggingPrinter.getOutput());
    }
}