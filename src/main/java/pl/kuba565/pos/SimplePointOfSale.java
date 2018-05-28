package pl.kuba565.pos;

import pl.kuba565.model.Product;
import pl.kuba565.model.ProductRepository;
import pl.kuba565.model.Receipt;
import pl.kuba565.device.*;
import pl.kuba565.model.StandardReceipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class SimplePointOfSale implements PointOfSale {
    private ProductRepository productRepository;
    private Display display;
    private Printer printer;
    private List<Product> scannedProductList;
    private BigDecimal totalSum;

    public SimplePointOfSale(Display display, Printer printer, ProductRepository productRepository, Scanner scanner) {
        this.display = display;
        this.printer = printer;
        this.productRepository = productRepository;
        scannedProductList = new ArrayList<>();
        totalSum = new BigDecimal(0.0);
        scanner.attach(this);
    }


    @Override
    public void exit() {
        calculateTotalSum();
        Receipt receipt = new StandardReceipt(scannedProductList, totalSum);
        printer.printReceipt(receipt);
        display.logTotalSum(totalSum);
    }

    private void calculateTotalSum() {
        if (scannedProductList.size() > 0) {
            for (Product currentProduct : scannedProductList) {
                totalSum = totalSum.add(currentProduct.getPrice());
            }
        }
    }

    @Override
    public Printer getPrinter() {
        return printer;
    }

    @Override
    public Display getDisplay() {
        return display;
    }


    @Override
    public void onScannedBarCode(Long barCode) {
        Product scannedProduct = productRepository.getByBarCode(barCode);
        if (scannedProduct != null) {
            display.logScannedProductNameAndPrice(scannedProduct);
            scannedProductList.add(scannedProduct);
        } else if (barCode == 0L) {
            display.logInvalidBarCode();
        } else {
            display.logProductNotFound();
        }
    }

}
