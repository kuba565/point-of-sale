package pl.kuba565.model;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class InMemoryProductRepositoryTest {
    @Test
    public void shouldAddProductToDataBase() {
        ProductRepository productRepository = new InMemoryProductRepository();
        Product mockProduct = new StandardProduct("mockProduct", new BigDecimal(0.0), 0L);
        //then
        productRepository.insert(mockProduct);
        //verify
        Product result = productRepository.getByBarCode(mockProduct.getBarCode());
        Assert.assertEquals(mockProduct, result);
    }
}