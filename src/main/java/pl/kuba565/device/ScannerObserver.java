package pl.kuba565.device;

public interface ScannerObserver {
    void onScannedBarCode(Long barCode);
}