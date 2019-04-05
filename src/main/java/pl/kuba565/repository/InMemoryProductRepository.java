package pl.kuba565.repository;

import pl.kuba565.model.Product;
import pl.kuba565.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
    private Map<Long, Product> productsMap = new HashMap<>();

    @Override
    public void add(Product product) {
        productsMap.put(product.getBarCode(), product);
    }

    @Override
    public Product getProductByBarCode(Long barCode) {
        return productsMap.get(barCode);
    }
}
