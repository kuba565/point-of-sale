package pl.kuba565.device;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.pos.PointOfSale;
import pl.kuba565.pos.SimplePointOfSale;
import pl.kuba565.model.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.model.ProductRepository;
import pl.kuba565.model.StandardProduct;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class ConsoleLoggingDisplayTest {

    @Test
    public void shouldLogScannedProductNameAndPrice() {
        Display display = new ConsoleLoggingDisplay();
        Product product = new StandardProduct("testProduct", new BigDecimal(2.0), 10L);
        //then
        display.logScannedProductNameAndPrice(product);
        //verify
        String result = display.getMessage();
        String expected = "DISPLAY: Scanned: testProduct price: 2";
        assertEquals(expected, result);
    }

    @Test
    public void shouldLogTotalSum() {
        Display display = new ConsoleLoggingDisplay();
        BigDecimal totalSum = new BigDecimal("2.23");
        //then
        display.logTotalSum(totalSum);
        //verify
        String result = display.getMessage();
        String expected = "DISPLAY: total sum: 2.23";
        assertEquals(expected, result);
    }
    @Test
    public void shouldLogProductNotFound() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);
        //then
        scanner.scanProductByBarCode(10L);

        //verify
        final String expected = "DISPLAY: Product not found.";
        Assert.assertEquals(expected, display.getMessage());
    }

    @Test
    public void shouldLogInvalidBarCode() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Display display = new ConsoleLoggingDisplay();
        Scanner scanner = new ProductScanner();
        PointOfSale pointOfSale = new SimplePointOfSale(display, new ConsoleLoggingPrinter(), productRepository, scanner);
        //then
        scanner.scanProductByBarCode(0L);

        //verify
        final String expected = "DISPLAY: Invalid bar code.";
        Assert.assertEquals(expected, display.getMessage());
    }
}