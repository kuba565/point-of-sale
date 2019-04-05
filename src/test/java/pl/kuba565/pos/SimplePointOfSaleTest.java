package pl.kuba565.pos;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.kuba565.model.StandardReceipt;
import pl.kuba565.repository.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.repository.ProductRepository;
import pl.kuba565.model.StandardProduct;
import pl.kuba565.scanner.*;
import pl.kuba565.view.ConsoleLoggingDisplay;
import pl.kuba565.view.ConsoleLoggingPrinter;
import pl.kuba565.view.Display;
import pl.kuba565.view.Printer;

import java.math.BigDecimal;
import java.util.Collections;

public class SimplePointOfSaleTest {

    @Test
    public void shouldLogMessagesToDisplay() {
        //given
        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);
        StandardProduct milk = new StandardProduct("milk", new BigDecimal(3.0), 2L);
        StandardProduct cheese = new StandardProduct("cheese", new BigDecimal(20.0), 3L);
        StandardProduct butter = new StandardProduct("butter", new BigDecimal(5.0), 4L);
        final ProductRepository productRepository = givenProductRepositoryWithProducts(bread, milk, cheese, butter);
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(1L);
        scanner.scanProductByBarCode(2L);
        scanner.scanProductByBarCode(10L);
        scanner.scanProductByBarCode(0L);
        pointOfSale.exit();

        //then
        final String expected = "DISPLAY: Scanned: bread price: 2.5" +
                "DISPLAY: Scanned: milk price: 3" +
                "DISPLAY: Product not found." +
                "DISPLAY: Invalid bar code." +
                "DISPLAY: total sum: 5.5";

        Assert.assertEquals(expected, pointOfSale.getDisplay().flush());
    }


    @Test
    public void shouldLogProductNotFoundToDisplay() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(10L);
        pointOfSale.exit();

        //then
        final String expected = "DISPLAY: Product not found."
                + "DISPLAY: total sum: 0";

        Assert.assertEquals(expected, pointOfSale.getDisplay().flush());
    }


    @Test
    public void shouldPutProductIntoProductDaoAndDisplayScanResult() {
        //given
        Product testProduct = new StandardProduct("testProduct", new BigDecimal("100.1"), 15L);
        final ProductRepository productRepository = givenProductRepositoryWithProducts(testProduct);
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(15L);
        pointOfSale.exit();

        //then
        final String expected = "DISPLAY: Scanned: testProduct price: 100.1"
                + "DISPLAY: total sum: 100.1";

        Assert.assertEquals(expected, pointOfSale.getDisplay().flush());
    }

    @Test
    public void shouldLogInvalidBarCodeToDisplay() {
        //given
        Display display = new ConsoleLoggingDisplay();
        final ProductRepository productRepository = new InMemoryProductRepository();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(0L);
        pointOfSale.exit();

        //then
        final String expected = "DISPLAY: Invalid bar code."
                + "DISPLAY: total sum: 0";

        Assert.assertEquals(expected, pointOfSale.getDisplay().flush());
    }

    @Test
    public void shouldLogProductToDisplay() {
        //given
        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);
        final ProductRepository productRepository = givenProductRepositoryWithProducts(bread);
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(1L);
        pointOfSale.exit();

        //then
        final String expected = "DISPLAY: Scanned: bread price: 2.5"
                + "DISPLAY: total sum: 2.5";

        Assert.assertEquals(expected, pointOfSale.getDisplay().flush());
    }

    @Test
    public void shouldSendMessagesToPrinter() {
        //given
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        StandardProduct bread = new StandardProduct("bread", new BigDecimal(2.5), 1L);
        final ProductRepository productRepository = givenProductRepositoryWithProducts(bread);
        Printer printer = Mockito.mock(Printer.class);
        PointOfSale pointOfSale = new SimplePointOfSale(display, printer, productRepository, scanner);

        //when
        scanner.scanProductByBarCode(1L);
        scanner.scanProductByBarCode(2L);
        pointOfSale.exit();

        //then
        StandardReceipt receipt = new StandardReceipt(Collections.singletonList(bread), new BigDecimal(2.5));
        Mockito.verify(printer).printReceipt(receipt);
    }

    private ProductRepository givenProductRepositoryWithProducts(Product... products) {
        ProductRepository productRepository = new InMemoryProductRepository();

        for (Product product : products) {
            productRepository.add(product);
        }
        return productRepository;
    }
}
