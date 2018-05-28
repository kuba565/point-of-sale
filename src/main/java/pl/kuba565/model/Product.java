package pl.kuba565.model;

import java.math.BigDecimal;

public interface Product {
    String getName();

    BigDecimal getPrice();

    Long getBarCode();
}
