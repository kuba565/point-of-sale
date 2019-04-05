package pl.kuba565.model;


import org.junit.Assert;
import org.junit.Test;
import pl.kuba565.repository.InMemoryProductRepository;
import pl.kuba565.repository.ProductRepository;

import java.math.BigDecimal;

public class InMemoryProductRepositoryTest {
    @Test
    public void shouldAddProductToDataBase() {
        //given
        ProductRepository productRepository = new InMemoryProductRepository();
        Product mockProduct = new StandardProduct("mockProduct", new BigDecimal(0.0), 0L);

        //when
        productRepository.add(mockProduct);

        //then
        Product result = productRepository.getProductByBarCode(mockProduct.getBarCode());

        Assert.assertEquals(mockProduct, result);
    }
}