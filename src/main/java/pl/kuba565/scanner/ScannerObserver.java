package pl.kuba565.scanner;

public interface ScannerObserver {
    void onScannedBarCode(Long barCode);
}