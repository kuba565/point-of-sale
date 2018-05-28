package pl.kuba565.model;

import java.math.BigDecimal;
import java.util.List;

public class StandardReceipt implements Receipt {
    private List<Product> scannedProductList;
    private BigDecimal totalSum;

    public StandardReceipt(List<Product> scannedProductList, BigDecimal totalSum) {
        this.scannedProductList = scannedProductList;
        this.totalSum = totalSum;
    }

    @Override
    public List<Product> getScannedProductList() {
        return scannedProductList;
    }

    @Override
    public BigDecimal getTotalSum() {
        return totalSum;
    }
}
