package pl.kuba565.scanner;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.pos.PointOfSale;
import pl.kuba565.pos.SimplePointOfSale;
import pl.kuba565.repository.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.repository.ProductRepository;
import pl.kuba565.model.StandardProduct;
import pl.kuba565.view.ConsoleLoggingDisplay;
import pl.kuba565.view.ConsoleLoggingPrinter;
import pl.kuba565.view.Display;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class ConsoleLoggingDisplayTest {
    @Test
    public void shouldLogScannedProductNameAndPrice() {
        //given
        Display display = new ConsoleLoggingDisplay();
        Product product = new StandardProduct("testProduct", new BigDecimal(2.0), 10L);

        //when
        display.logScannedProductNameAndPrice(product);
        String result = display.flush();

        //then
        String expected = new StringBuilder("DISPLAY: Scanned: testProduct price: 2")
                .append(System.lineSeparator())
                .toString();

        assertEquals(expected, result);
    }

    @Test
    public void shouldLogTotalSum() {
        //given
        Display display = new ConsoleLoggingDisplay();
        BigDecimal totalSum = new BigDecimal("2.23");

        //when
        display.logTotalSum(totalSum);

        //then
        String result = display.flush();
        String expected = "DISPLAY: total sum: 2.23";

        assertEquals(expected, result);
    }

    @Test
    public void shouldLogProductNotFound() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(10L);

        //then
        final String expected = new StringBuilder("DISPLAY: Product not found.")
                .append(System.lineSeparator())
                .toString();

        Assert.assertEquals(expected, display.flush());
    }

    @Test
    public void shouldLogInvalidBarCode() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);

        //when
        scanner.scanProductByBarCode(0L);

        //then
        final String expected = new StringBuilder("DISPLAY: Invalid bar code.")
                .append(System.lineSeparator())
                .toString();

        Assert.assertEquals(expected, display.flush());
    }
}