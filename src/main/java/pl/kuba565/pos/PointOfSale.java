package pl.kuba565.pos;

import pl.kuba565.view.Display;
import pl.kuba565.view.Printer;
import pl.kuba565.scanner.ScannerObserver;

public interface PointOfSale extends ScannerObserver {

    void exit();

    Printer getPrinter();

    Display getDisplay();
}
