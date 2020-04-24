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
public class WarehouseException extends Exception {

    /**
     * Creates new WarehouseException
     *
     * @param message
     */
    public WarehouseException(String message) {
        super("Warehouse: " + message);
    }
}
