package ProjectAsm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Quoc An
 */
public class Warehouse {

    private int whId;       //Warehouse Id (PK)
    private String whName;     //Name of warehouse
    private String whAddress;  //Address of warehouse

    /**
     * Gets the id of warehouse
     *
     * @return
     */
    public int getWhId() {
        return whId;
    }

    /**
     * Sets id for warehouse
     *
     * @param whId warehouse id must be a positive integer
     * @throws WarehouseException
     */
    public void setWhId(int whId) throws WarehouseException {

        if (whId < 0) {
            throw new WarehouseException("Warehouse ID must be a positive integer");
        } else {
            this.whId = whId;
        }
    }

    /**
     * Gets the name of Warehouse
     *
     * @return
     */
    public String getWhName() {
        return whName;
    }

    /**
     * Sets name for Warehouse
     *
     * @param whName warehouse name cannot be empty
     * @throws WarehouseException
     */
    public void setWhName(String whName) throws WarehouseException {
        if (whName.equals("")) {
            throw new WarehouseException("Name of Warehouse can't be empty!");
        } else {
            this.whName = whName;
        }
    }

    /**
     * Gets the address of Warehouse
     *
     * @return
     */
    public String getWhAddress() {
        return whAddress;
    }

    /**
     * Sets address for warehouse
     *
     * @param whAddress warehouse address cannot be empty
     * @throws WarehouseException
     */
    public void setWhAddress(String whAddress) throws WarehouseException {
        if (whAddress.equals("")) {
            throw new WarehouseException("Address of Warehouse can't be empty!");
        } else {
            this.whAddress = whAddress;
        }
    }
    /**
     * Create new warehouse
     *
     * @param whId
     * @param whName
     * @param whAddress
     * @throws WarehouseException
     */
    public Warehouse(int whId, String whName, String whAddress) throws WarehouseException {
        this.setWhId(whId);
        this.setWhAddress(whAddress);
        this.setWhName(whName);
        //this.setShId(shId);
    }

}
