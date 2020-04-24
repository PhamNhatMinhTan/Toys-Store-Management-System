package ProjectAsm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */
public class ProductException extends Exception {

    /**
     * Creates new ProductException
     *
     * @param message
     */
    public ProductException(String message) {
        super("Product: " + message);
    }
}
