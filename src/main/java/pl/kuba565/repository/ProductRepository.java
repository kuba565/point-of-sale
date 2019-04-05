package pl.kuba565.repository;

import pl.kuba565.model.Product;

public interface ProductRepository {
    void add(Product product);

    Product getProductByBarCode(Long barCode);
}
