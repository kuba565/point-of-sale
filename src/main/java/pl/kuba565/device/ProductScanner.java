package pl.kuba565.device;


import pl.kuba565.pos.PointOfSale;

import java.util.ArrayList;
import java.util.List;

public class ProductScanner implements Scanner {
    private List<ScannerObserver> scannerObservers;

    public ProductScanner() {
        scannerObservers = new ArrayList<>();
    }

    @Override
    public Long scanProductByBarCode(Long barCode) {
        notifyObservers(barCode);
        return barCode;
    }


    public void attach(PointOfSale pointOfSale) {
        scannerObservers.add(pointOfSale);
    }

    private void notifyObservers(Long barCode) {
        for (ScannerObserver pointOfSale : scannerObservers) {
            pointOfSale.onScannedBarCode(barCode);
        }
    }
}
