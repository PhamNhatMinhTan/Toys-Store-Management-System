package ProjectAsm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Tan
 */
public class Product {

    //declare variable
    private int proId;              //Create product ID (Primary key)
    private String proName;         //Create product name
    private int bId;                //Create brand ID of product (forein key)
    private String size;            //Create size of product
    private float price;            //Create the cost
    private String material;        //Create material of product
    private int ages;               //Create the age appropriate
    private int quantity;           //Create quantity of product

    /**
     * get product id (PK)
     *
     * @return
     */
    public int getProId() {
        return proId;
    }

    /**
     * set product id (PK)
     *
     * @param proId
     * @throws ProductException
     */
    public void setProId(int proId) throws ProductException {
        if (proId <= 0) {
            throw new ProductException("Your proId must be greater than 0!");
        } else {
            this.proId = proId;
        }

    }

    /**
     * get name of product
     *
     * @return
     */
    public String getProName() {
        return proName;
    }

    /**
     * set name of product
     *
     * @param proName
     * @throws ProductException
     */
    public void setProName(String proName) throws ProductException {
        if (proName.equals("")) {
            throw new ProductException("Your product name can't be empty!");
        } else {
            this.proName = proName;
        }
    }

    /**
     * get brand of product
     *
     * @return
     */
    public int getBId() {
        return bId;
    }

    /**
     * set brand of product
     *
     * @param bId
     * @throws ProductException
     */
    public void setBId(int bId) throws ProductException {
        if (bId <= 0) {
            throw new ProductException("The brand ID must be greater than 0!");
        } else {
            this.bId = bId;
        }
    }

    /**
     * get size of product
     *
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     * set size of product
     *
     * @param size
     * @throws ProductException
     */
    public void setSize(String size) throws ProductException {
        if (size.equals("")) {
            throw new ProductException("Size of product can't be empty!");
        } else {
            this.size = size;
        }
    }

    /**
     * get product price
     *
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * set product price
     *
     * @param price
     * @throws ProductException
     */
    public void setPrice(float price) throws ProductException {
        if (price <= 0) {
            throw new ProductException("The price of product can't be smaller 0!");
        } else {
            this.price = price;
        }
    }

    /**
     * get material of product
     *
     * @return
     */
    public String getMaterial() {
        return material;
    }

    /**
     * set material of product
     *
     * @param material
     * @throws ProductException
     */
    public void setMaterial(String material) throws ProductException {
        if (material.equals("")) {
            throw new ProductException("The material of product can't be empty!");
        } else {
            this.material = material;
        }
    }

    /**
     * get the ages appropriate
     *
     * @return
     */
    public int getAges() {
        return ages;
    }

    /**
     * set the ages appropriate
     *
     * @param ages
     * @throws ProductException
     */
    public void setAges(int ages) throws ProductException {
        if (ages <= 0 || ages > 160) {
            throw new ProductException("The ages for user must be greater than 0 and smaller than 160!");
        } else {
            this.ages = ages;
        }
    }

    /**
     * get quantity of product
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set quantity of product
     *
     * @param quantity
     * @throws ProductException
     */
    public void setQuantity(int quantity) throws ProductException {
        if (quantity <= 0) {
            throw new ProductException("The quantity can't smaller than 0!");
        } else {
            this.quantity = quantity;
        }
    }

    /**
     * Create new product
     *
     * @param proId
     * @param proName
     * @param bId
     * @param size
     * @param price
     * @param material
     * @param ages
     * @param quantity
     * @throws ProductException
     */
    public Product(int proId, String proName, int bId, String size, float price,
            String material, int ages, int quantity) throws ProductException {
        this.setProId(proId);
        this.setProName(proName);
        this.setBId(bId);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setAges(ages);
        this.setMaterial(material);
        this.setSize(size);
    }

    @Override
    public String toString() {
        return this.proName
                + this.bId
                + this.size
                + this.price
                + this.material
                + this.ages
                + this.quantity;
    }
}
