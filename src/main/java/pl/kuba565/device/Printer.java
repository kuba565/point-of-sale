package pl.kuba565.device;

import pl.kuba565.model.Receipt;

public interface Printer {
    void printReceipt(Receipt receipt);

    String getOutput();
}
