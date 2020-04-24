package ProjectAsm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Tan
 */
public class ShopManagement {

    private String S_FILE;              //The URL of data file that stores all branch of shop
    private int numberOfBranch;         //The number of branch that boss of shop opened
    private ArrayList<Shop> shops;      //All instance of shop
    private WarehouseManagement whm;    //Instance of WarehouseManagement
    private ProductManagement pm;       //Instance of ProductManagement

    /**
     * Create instance for shop management
     *
     * @param S_FILE
     * @param whm
     * @param pm
     * @throws ShopException
     */
    public ShopManagement(String S_FILE, WarehouseManagement whm, ProductManagement pm) throws ShopException {
        if (S_FILE.equals("")) {
            throw new ShopException("The URL of shop data file can't be empty!");
        } else {
            this.S_FILE = S_FILE;

            //Create the ArrayList to store the branches
            this.shops = new ArrayList<Shop>();

            //So, the number of branch will be equal 0
            this.numberOfBranch = 0;

            //Inits the Warehouse management
            this.whm = whm;

            //Inits the Product maagement
            this.pm = pm;
        }
    }

    /**
     * Loads data of shop from file and stores it into array list
     *
     * @throws IOException
     * @throws ShopException
     */
    public void loadShops() throws IOException, ShopException {
        File sFile = new File(S_FILE);

        //check is file created
        if (!sFile.exists()) {
            System.out.println(//"\n---------------------------\n" +
                    "The data file Shop.txt is not exist. "
                    + "Creating new data file Shop.txt... " + "Done!");

            //New file with the number of is 0
            this.numberOfBranch = 0;
        } else {
            //If file is exist, so loading that data file
            System.out.println(//"n-------------------------" + 
                    "The data file Shop.txt is found. "
                    + "Data of file is loading...");

            //Loads data file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(S_FILE))) {
                String line, shId, shName, shAddress;

                line = br.readLine();
                this.numberOfBranch = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfBranch; i++) {
                    //Read branch information
                    shId = br.readLine();
                    shName = br.readLine();
                    shAddress = br.readLine();

                    //Create new instance of branch and 
                    this.shops.add(new Shop(Integer.parseInt(shId), shName, shAddress));
                }
            }
            System.out.println("Done! We have total [" + this.numberOfBranch + " branch]");
        }
    }

    /**
     * Add new branch of shop
     *
     * @param shName
     * @param shAddress
     * @return
     * @throws ShopException
     */
    public int addShop(String shName, String shAddress) throws ShopException {
        this.shops.add(new Shop(++this.numberOfBranch, shName, shAddress));
        return this.numberOfBranch;
    }

    /**
     * Find index of branch and return that index
     *
     * @param shId
     * @return
     */
    public int findShop(int shId) {
        for (int i = 0; i < this.shops.size(); i++) {
            Shop s = this.shops.get(i);
            if (s.getShId() == shId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find the branch by index of branch
     *
     * @param shId
     * @return
     */
    public Shop getShop(int shId) {
        int viTri = this.findShop(shId);
        if (viTri == -1) {
            return null;
        } else {
            return this.shops.get(viTri);
        }
    }

    /**
     * Find shop by name of shop and return index of shop
     *
     * @param userInput
     * @return
     */
    public int searchIdShop(String userInput) {
        for (int i = 0; i < this.shops.size(); i++) {
            Shop s = this.shops.get(i);
            if (s.getShName() == userInput) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find shop instance by shop name
     *
     * @param userInput
     * @return
     */
    public Shop searchShop(String userInput) {
        int viTri = this.searchIdShop(userInput);
        if (viTri == -1) {
            return null;
        } else {
            return this.shops.get(viTri);
        }
    }

    /**
     * approximate search shop
     *
     * @param userInput
     */
    void approximateSearchShop(String userInput) {
        int dem = 0;
        for (int i = 0; i < this.shops.size(); i++) {
            Shop s = this.shops.get(i);
            String name = s.getShName();
            if (name.toLowerCase().indexOf(userInput) != -1) {
                System.out.println(name);
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("NOT FOUND!");
        }
    }

    /**
     * Save data of ArrayList into file
     *
     * @throws IOException
     */
    public void saveShop() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(S_FILE), false);
        try {
            System.out.println(//"\n-----------------------" + 
                    "\nThe data of shop is saving into file Shop.txt...");

            //Write number of branch
            fw.append(String.valueOf(this.numberOfBranch) + "\n");

            for (int i = 0; i < this.numberOfBranch; i++) {
                int shId = this.shops.get(i).getShId();
                String shName = this.shops.get(i).getShName();
                String shAddress = this.shops.get(i).getShAddress();

                //Write branch's information into data file
                fw.append(String.valueOf(shId) + "\n");
                fw.append(shName + "\n");
                fw.append(shAddress + "\n");
            }
        } finally {
            //Save data file (from RAM to HDD)
            fw.close();
            System.out.println("Done! Saved [" + this.numberOfBranch + "branch]");
        }
    }

    /**
     * Show shop include all information of shop
     *
     * @param shId
     * @return
     */
    public String showShop(int shId) {
        Shop s = getShop(shId);
        int sList = this.shops.size();
        String chuoi = "";
        //chuoi += s.toString();
        chuoi += "Shop's name     : " + s.getShName() + "\n"
                + "Shop's address : " + s.getShAddress();
        return chuoi;
    }

    /**
     * Displays all Shop (branch)
     */
    public void showAllShop() {
        int sNo = 1;
        for (int i = 0; i < this.shops.size(); i++, sNo++) {
            Shop s = this.shops.get(i);

            System.out.println(sNo + ". " + showShop(s.getShId()));
        }
    }
}
