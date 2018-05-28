package pl.kuba565.model;

import java.math.BigDecimal;

public class StandardProduct implements Product {
    private String name;
    private BigDecimal price;
    private Long id;


    public StandardProduct(String name, BigDecimal price, Long id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Long getBarCode() {
        return id;
    }
}
