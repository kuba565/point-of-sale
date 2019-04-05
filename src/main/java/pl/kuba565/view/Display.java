package pl.kuba565.view;

import pl.kuba565.model.Product;

import java.math.BigDecimal;

public interface Display {
    void logProductNotFound();

    void logInvalidBarCode();

    void logScannedProductNameAndPrice(Product scannedProduct);

    void logTotalSum(BigDecimal totalSum);

    String flush();
}
