package pl.kuba565.pos;

import pl.kuba565.device.Display;
import pl.kuba565.device.Printer;
import pl.kuba565.device.ScannerObserver;

public interface PointOfSale extends ScannerObserver {

    void exit();

    Printer getPrinter();

    Display getDisplay();
}
