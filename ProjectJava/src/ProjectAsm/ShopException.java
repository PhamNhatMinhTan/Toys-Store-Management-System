package ProjectAsm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Thong
 */
public class ShopException extends Exception {

    /**
     * Creates new ShopException
     *
     * @param message
     */
    public ShopException(String message) {
        super("Shop: " + message);
    }
}
