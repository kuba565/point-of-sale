package pl.kuba565.scanner;

public interface Scanner {
    Long scanProductByBarCode(Long barCode);

    void registerObserver(ScannerObserver scannerObserver);
}
