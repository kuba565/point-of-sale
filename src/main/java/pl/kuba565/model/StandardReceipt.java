package pl.kuba565.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class StandardReceipt implements Receipt {
        private List<Product> productList;
        private BigDecimal totalSum;

        public StandardReceipt(List<Product> productList, BigDecimal totalSum) {
            this.productList = productList;
            this.totalSum = totalSum;
        }

        @Override
        public List<Product> getProductList() {
            return productList;
        }

        @Override
        public BigDecimal getTotalSum() {
            return totalSum;
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardReceipt that = (StandardReceipt) o;
        return Objects.equals(productList, that.productList) &&
                Objects.equals(totalSum, that.totalSum);
    }

}
