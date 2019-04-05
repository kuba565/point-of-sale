package pl.kuba565.view;

import pl.kuba565.model.Receipt;

public interface Printer {
    String printReceipt(Receipt receipt);
    String getOutput();
}
