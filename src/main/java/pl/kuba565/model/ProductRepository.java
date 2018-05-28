package pl.kuba565.model;

public interface ProductRepository {
    void insert(Product product);

    Product getByBarCode(Long id);

}
