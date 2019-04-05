package pl.kuba565.pos;

import pl.kuba565.view.Display;
import pl.kuba565.view.Printer;
import pl.kuba565.scanner.Scanner;
import pl.kuba565.model.Product;
import pl.kuba565.repository.ProductRepository;
import pl.kuba565.model.Receipt;
import pl.kuba565.model.StandardReceipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class SimplePointOfSale implements PointOfSale {
    private ProductRepository productRepository;
    private Display display;
    private Printer printer;
    private List<Product> scannedProductList;

    public SimplePointOfSale(Display display, Printer printer, ProductRepository productRepository, Scanner scanner) {
        this.display = display;
        this.printer = printer;
        this.productRepository = productRepository;
        scannedProductList = new ArrayList<>();
        scanner.registerObserver(this);
    }

    @Override
    public void exit() {
        BigDecimal totalSum = calculateTotalSum();
        Receipt receipt = new StandardReceipt(scannedProductList, totalSum);
        printer.printReceipt(receipt);
        display.logTotalSum(totalSum);
    }

    private BigDecimal calculateTotalSum() {
        BigDecimal totalSum = new BigDecimal(0L);
        if (scannedProductList.size() > 0) {
            for (Product currentProduct : scannedProductList) {
                totalSum = totalSum.add(currentProduct.getPrice());
            }
        }
        return totalSum;
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
        Product scannedProduct = productRepository.getProductByBarCode(barCode);
        boolean scannedProductIsNull = scannedProduct != null;

        if (scannedProductIsNull) {
            display.logScannedProductNameAndPrice(scannedProduct);
            scannedProductList.add(scannedProduct);
        } else {
            boolean barCodeIsInvalid = barCode == 0L;

            if (barCodeIsInvalid) {
                display.logInvalidBarCode();
            } else {
                display.logProductNotFound();
            }
        }
    }
}
