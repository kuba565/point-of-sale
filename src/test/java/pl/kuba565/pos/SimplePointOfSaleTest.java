package pl.kuba565.pos;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.model.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.model.ProductRepository;
import pl.kuba565.model.StandardProduct;
import pl.kuba565.device.*;

import java.math.BigDecimal;

public class SimplePointOfSaleTest {

    @Test
    public void shouldLogMessagesToDisplay() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();

        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);
        StandardProduct milk = new StandardProduct("milk", new BigDecimal(3.0), 2L);
        StandardProduct cheese = new StandardProduct("cheese", new BigDecimal(20.0), 3L);
        StandardProduct butter = new StandardProduct("butter", new BigDecimal(5.0), 4L);

        productRepository.insert(bread);
        productRepository.insert(milk);
        productRepository.insert(cheese);
        productRepository.insert(butter);

        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);
        //then
        scanner.scanProductByBarCode(1L);
        scanner.scanProductByBarCode(2L);
        scanner.scanProductByBarCode(10L);
        scanner.scanProductByBarCode(0L);
        pointOfSale.exit();

        //verify
        final String expected = "DISPLAY: Scanned: bread price: 2.5" +
                "DISPLAY: Scanned: milk price: 3" +
                "DISPLAY: Product not found." +
                "DISPLAY: Invalid bar code." +
                "DISPLAY: total sum: 5.5";

        Assert.assertEquals(expected, pointOfSale.getDisplay().getMessage());
    }

    @Test
    public void shouldLogProductNotFoundToDisplay() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);
        //then
        scanner.scanProductByBarCode(10L);
        pointOfSale.exit();

        //verify
        final String expected = "DISPLAY: Product not found."
                + "DISPLAY: total sum: 0";

        Assert.assertEquals(expected, pointOfSale.getDisplay().getMessage());
    }


    @Test
    public void shouldPutProductIntoProductDaoAndDisplayScanResult() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();

        Scanner scanner = new ProductScanner();
        Product testProduct = new StandardProduct("testProduct", new BigDecimal("100.1"), 15L);

        //then
        productRepository.insert(testProduct);

        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);
        scanner.scanProductByBarCode(15L);
        pointOfSale.exit();

        //verify
        final String expected = "DISPLAY: Scanned: testProduct price: 100.1"
                + "DISPLAY: total sum: 100.1";

        Assert.assertEquals(expected, pointOfSale.getDisplay().getMessage());
    }

    @Test
    public void shouldLogInvalidBarCodeToDisplay() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();

        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //then
        scanner.scanProductByBarCode(0L);
        pointOfSale.exit();

        //verify
        final String expected = "DISPLAY: Invalid bar code."
                + "DISPLAY: total sum: 0";

        Assert.assertEquals(expected, pointOfSale.getDisplay().getMessage());
    }

    @Test
    public void shouldLogProductToDisplay() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();

        Scanner scanner = new ProductScanner();
        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);
        productRepository.insert(bread);
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //then
        scanner.scanProductByBarCode(1L);
        pointOfSale.exit();

        //verify
        final String expected = "DISPLAY: Scanned: bread price: 2.5"
                + "DISPLAY: total sum: 2.5";

        Assert.assertEquals(expected, pointOfSale.getDisplay().getMessage());
    }


    @Test
    public void shouldSendMsgsToPrinter() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();

        Scanner scanner = new ProductScanner();
        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);

        productRepository.insert(bread);

        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //then
        scanner.scanProductByBarCode(1L);
        scanner.scanProductByBarCode(2L);
        scanner.scanProductByBarCode(10L);
        scanner.scanProductByBarCode(0L);
        pointOfSale.exit();


        //verify
        final String expected = new StringBuilder(System.lineSeparator())
                .append("RECEIPT:")
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator())
                .append("1. name: bread price: 2.5")
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator())
                .append("total receipt price: 2.5")
                .append(System.lineSeparator())
                .append("-----------")
                .append(System.lineSeparator())
                .toString();

        Assert.assertEquals(expected, pointOfSale.getPrinter().getOutput());
    }
}