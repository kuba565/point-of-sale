package pl.kuba565.model;

import java.math.BigDecimal;
import java.util.List;

public interface Receipt {
    List<Product> getProductList();

    BigDecimal getTotalSum();
}
