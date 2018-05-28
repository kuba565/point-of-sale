package pl.kuba565.device;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.model.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.model.ProductRepository;
import pl.kuba565.model.StandardProduct;

import java.math.BigDecimal;

public class ProductScannerTest {

    @Test
    public void shouldFindProduct() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Product testProduct = new StandardProduct("testProduct", new BigDecimal("1.0"), 1L);
        productRepository.insert(testProduct);
        Scanner scanner = new ProductScanner();

        //then
        Long scannedProductBarCode = scanner.scanProductByBarCode(1L);
        Product scannedProduct = productRepository.getByBarCode(scannedProductBarCode);

        //verify
        Assert.assertEquals(testProduct, scannedProduct);
    }

    @Test
    public void shouldNotFindProductNotInDB() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Scanner scanner = new ProductScanner();

        //then
        Long scannedProductBarCode = scanner.scanProductByBarCode(1L);
        Product scannedProduct = productRepository.getByBarCode(scannedProductBarCode);

        //verify
        Assert.assertNull(scannedProduct);
    }

    @Test
    public void shouldNotFindProductWithWrongBarcode() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Scanner scanner = new ProductScanner();

        //then
        Long scannedProductBarCode = scanner.scanProductByBarCode(0L);
        Product scannedProduct = productRepository.getByBarCode(scannedProductBarCode);

        //verify
        Assert.assertNull(scannedProduct);
    }
}