package pl.kuba565.scanner;

import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.repository.InMemoryProductRepository;
import pl.kuba565.model.Product;
import pl.kuba565.repository.ProductRepository;
import pl.kuba565.model.StandardProduct;

import java.math.BigDecimal;

public class ProductScannerTest {
    @Test
    public void shouldFindProduct() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Product testProduct = new StandardProduct("testProduct", new BigDecimal("1.0"), 1L);
        productRepository.add(testProduct);
        Scanner scanner = new ProductScanner();

        //when
        Long scannedProductBarCode = scanner.scanProductByBarCode(1L);
        Product scannedProduct = productRepository.getProductByBarCode(scannedProductBarCode);

        //verify
        Assert.assertEquals(testProduct, scannedProduct);
    }

    @Test
    public void shouldNotFindProductNotInDB() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Scanner scanner = new ProductScanner();

        //when
        Long scannedProductBarCode = scanner.scanProductByBarCode(1L);

        //then
        Product scannedProduct = productRepository.getProductByBarCode(scannedProductBarCode);
        Assert.assertNull(scannedProduct);
    }

    @Test
    public void shouldNotFindProductWithWrongBarcode() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Scanner scanner = new ProductScanner();

        //when
        Long scannedProductBarCode = scanner.scanProductByBarCode(0L);

        //then
        Product scannedProduct = productRepository.getProductByBarCode(scannedProductBarCode);
        Assert.assertNull(scannedProduct);
    }
}