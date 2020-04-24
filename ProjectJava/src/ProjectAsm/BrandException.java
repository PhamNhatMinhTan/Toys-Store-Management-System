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
public class BrandException extends Exception {

    /**
     * Creates new BrandException
     *
     * @param message
     */
    public BrandException(String message) {
        super("Brand: " + message);
    }
}
