package pl.kuba565.model;

import java.util.HashMap;
import java.util.Map;


public class InMemoryProductRepository implements ProductRepository {
    private Map<Long, Product> productsMap = new HashMap<>();

    @Override
    public void insert(Product product) {
        productsMap.put(product.getBarCode(), product);
    }

    @Override
    public Product getByBarCode(Long id) {
        return productsMap.get(id);
    }


}
