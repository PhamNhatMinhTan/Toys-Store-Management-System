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
public class Nation {

    private int nationId;
    private String nationName;

    /**
     * get nation id
     *
     * @return
     */
    public int getNationId() {
        return nationId;
    }

    /**
     * set nation id
     *
     * @param nationId
     * @throws NationException
     */
    public void setNationId(int nationId) throws NationException {
        if (nationId <= 0) {
            throw new NationException("ID of nation must be greater than 0!");
        } else {
            this.nationId = nationId;
        }
    }

    /**
     * get name of nation
     *
     * @return
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * set name of nation
     *
     * @param nationName
     * @throws NationException
     */
    public void setNationName(String nationName) throws NationException {
        if (nationName.equals("")) {
            throw new NationException("Name of nation can't be empty!");
        } else {
            this.nationName = nationName;
        }
    }

    /**
     * create new nation
     *
     * @param nationId
     * @param nationName
     * @throws NationException
     */
    public Nation(int nationId, String nationName) throws NationException {
        this.setNationId(nationId);
        this.setNationName(nationName);
    }

    @Override
    public String toString() {
        return this.nationName;
    }
}
