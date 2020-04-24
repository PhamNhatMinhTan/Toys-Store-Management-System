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
public class NationException extends Exception {

    /**
     * Creates new NationException
     *
     * @param message
     */
    public NationException(String message) {
        super("Nation: " + message);
    }
}
