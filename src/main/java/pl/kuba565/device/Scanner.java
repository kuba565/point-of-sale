package pl.kuba565.device;

import pl.kuba565.pos.PointOfSale;

public interface Scanner {
    Long scanProductByBarCode(Long id);

    void attach(PointOfSale pointOfSale);
}
