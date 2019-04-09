package pl.kuba565.scanner;


import java.util.ArrayList;
import java.util.List;

public class ProductScanner implements Scanner {
    private List<ScannerObserver> scannerObservers;

    public ProductScanner() {
        scannerObservers = new ArrayList<>();
    }

    @Override
    public Long scanProductByBarCode(Long barCode) {
        sendBarCodeToDevices(barCode);
        return barCode;
    }

    public void registerObserver(ScannerObserver scannerObserver) {
        scannerObservers.add(scannerObserver);
    }

    private void sendBarCodeToDevices(Long barCode) {
        scannerObservers.forEach(pointOfSale -> pointOfSale.onScannedBarCode(barCode));
    }
}
