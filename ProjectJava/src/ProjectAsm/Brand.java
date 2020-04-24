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
public class Brand {

    private int bId;            //PK key
    private String bName;       // brand name
    private int nationId;    // FK key

    /**
     * get brand id
     *
     * @return
     */
    public int getBId() {
        return bId;
    }

    /**
     * set brand id
     *
     * @param bId
     * @throws BrandException
     */
    public void setBId(int bId) throws BrandException {
        if (bId <= 0) {
            throw new BrandException("Your proId must be greater than 0!");
        } else {
            this.bId = bId;
        }
    }

    /**
     * get brand name
     *
     * @return
     */
    public String getBName() {
        return bName;
    }

    /**
     * set brand name
     *
     * @param bName
     * @throws BrandException
     */
    public void setBName(String bName) throws BrandException {
        if (bName.equals("")) {
            throw new BrandException("Name of brand can't be empty!");
        } else {
            this.bName = bName;
        }
    }

    /**
     * *
     * get nation id
     *
     * @return
     */
    public int getNationId() {
        return nationId;
    }

    /**
     * set foreign key nation id for brand
     *
     * @param nationId
     * @throws BrandException
     */
    public void setNationId(int nationId) throws BrandException {
        if (nationId <= 0) {
            throw new BrandException("Nation ID must be greater than 0!");
        } else {
            this.nationId = nationId;
        }
    }

    /**
     * create new brand
     *
     * @param bId
     * @param bName
     * @param nationId
     * @throws BrandException
     */
    public Brand(int bId, String bName, int nationId) throws BrandException {
        this.setBId(bId);
        this.setBName(bName);
        this.setNationId(nationId);
    }

}
