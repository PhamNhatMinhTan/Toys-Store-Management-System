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
public class Shop {

    private int shId;
    private String shName;
    private String shAddress;

    /**
     * Gets the id of shop
     *
     * @return
     */
    public int getShId() {
        return shId;
    }

    /**
     * Sets id for shop
     *
     * @param shId shop id must be a positive integer
     * @throws ShopException
     */
    public void setShId(int shId) throws ShopException {

        if (shId <= 0) {
            throw new ShopException("Shop ID must be a positive integer");
        } else {
            this.shId = shId;
        }
    }

    /**
     * Gets the name of shop
     *
     * @return
     */
    public String getShName() {
        return shName;
    }

    /**
     * Sets name for shop
     *
     * @param shName shop name cannot be empty
     * @throws ShopException
     */
    public void setShName(String shName) throws ShopException {
        if (shName.equals("")) {
            throw new ShopException("Name of shop can't be empty");
        } else {
            this.shName = shName;
        }
    }

    /**
     * Gets the address of shop
     *
     * @return
     */
    public String getShAddress() {
        return shAddress;
    }

    /**
     * Sets address for shop
     *
     * @param shAddress shop address cannot be empty
     * @throws ShopException
     */
    public void setShAddress(String shAddress) throws ShopException {
        if (shName.equals("")) {
            throw new ShopException("Address of shop can't be empty");
        } else {
            this.shAddress = shAddress;
        }
    }

    /**
     * Creates new shop
     *
     * @param shId
     * @param shName
     * @param shAddress
     * @throws ShopException
     */
    public Shop(int shId, String shName, String shAddress) throws ShopException {
        this.setShId(shId);
        this.setShName(shName);
        this.setShAddress(shAddress);
    }
}
