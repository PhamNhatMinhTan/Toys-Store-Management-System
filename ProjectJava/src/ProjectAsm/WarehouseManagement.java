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
 * @author Dell
 */
public class WarehouseManagement {

    private String Wh_FILE;                      //The URL of data file that stores all warehouse
    private int numberOfWarehouse;              //Number of questions that stored in data file
    private ArrayList<Warehouse> warehouses;    //All instances of warehouse
    private WarehouseManagement whm;             //Instance of WarehouseManagement

    /**
     * Creates instance for warehouse management
     *
     * @param Wh_FILE
     * @param wm
     * @throws WarehouseManagement.WarehouseException
     */
    public WarehouseManagement(String Wh_FILE) throws WarehouseException {
        if (Wh_FILE.equals("")) {
            throw new WarehouseException("The URL of answer data file can't be empty!");
        } else {
            //Inits the URL of data file thats stores question bank
            this.Wh_FILE = Wh_FILE;

            //Creates empty question bank
            this.warehouses = new ArrayList<Warehouse>();

            //So, the number of warehouse is 0
            this.numberOfWarehouse = 0;

            //Inits the answer management
            this.whm = whm;
        }
    }

    /**
     * Loads data of Warehouse from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws WarehouseException
     */
    public void loadWarehouse() throws IOException, WarehouseException {
        File whFile = new File(Wh_FILE);
        //Checks is file created
        if (!whFile.exists()) {
            whFile.createNewFile(); //If not, creates new file
            System.out.println(
                    //"\n--------------------" + 
                    "The data file warehouse.txt is not exits. "
                    + "Creating new data file warehouse.txt..."
                    + "Done!");
            this.numberOfWarehouse = 0; //New data file with the number of warehouse is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print(
                    //"\n--------------------" + 
                    "\nThe data file warehouse.txt is found. "
                    + "Data of warehouse is loading...");

            //Loads text file into buffer
            BufferedReader br = new BufferedReader(new FileReader(Wh_FILE));
            try {
                String line, whId, whName, whAddress; //shId;

                //Reads number of warehouse
                line = br.readLine();
                this.numberOfWarehouse = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfWarehouse; i++) {
                    //Reads answer's information
                    whId = br.readLine();
                    whName = br.readLine();
                    whAddress = br.readLine();
                    //shId      = br.readLine();

                    //Create new instance of Answer and adds to answer bank
                    this.warehouses.add(new Warehouse(Integer.parseInt(whId), whName, whAddress));
                }
            } finally {
                br.close();
            }
            System.out.println("Done! You have total [" + this.numberOfWarehouse + " warehouse]");
        }
    }

    /**
     * Gets number of warehouse
     *
     * @return
     */
    public int getSize() {
        return this.numberOfWarehouse;
    }

    /**
     * Adds new question to question bank
     *
     * @param whName
     * @param whAddress
     * @return
     * @throws WarehouseException
     */
    public int addWarehouse(String whName, String whAddress) throws WarehouseException {
        this.warehouses.add(new Warehouse(++this.numberOfWarehouse, whName, whAddress));
        return this.numberOfWarehouse;
    }

    /**
     * Finds warehouse by warehouse id and return the index of this warehouse
     *
     * @param whId
     * @return
     */
    public int findWarehouse(int whId) {
        for (int i = 0; i < this.warehouses.size(); i++) {
            Warehouse q = this.warehouses.get(i);
            if (q.getWhId() == whId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the warehouse instance by warehouse id
     *
     * @param whId
     * @return
     */
    public Warehouse getWarehouse(int whId) {
        int idx = this.findWarehouse(whId);
        if (idx == -1) {
            return null;
        } else {
            return this.warehouses.get(idx);
        }
    }

    /**
     * Saves warehouse bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveWarehouse() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(Wh_FILE), false);

        try {
            System.out.print(
                    //"\n--------------------" + 
                    "\nWarehouse is saving into data file warehouse.txt...");

            //Writes number of warehouse
            fw.append(String.valueOf(this.numberOfWarehouse) + "\n");

            for (int i = 0; i < this.numberOfWarehouse; i++) {
                //Inits warehouse's information
                int whId = this.warehouses.get(i).getWhId();
                String whName = this.warehouses.get(i).getWhName();
                String whAddress = this.warehouses.get(i).getWhAddress();

                //Writes warehouse's information into data file
                fw.append(String.valueOf(whId) + "\n");
                fw.append(whName + "\n");
                fw.append(whAddress + "\n");
            }
        } finally {
            //Saves data file (from RAM to HDD)
            fw.close();
            System.out.println("Done! [" + this.numberOfWarehouse + " warehouses]");
        }
    }

    /**
     * Find warehouse by name of warehouse and return index of warehouse
     *
     * @param userInput
     * @return
     */
    public int searchIdWarehouse(String userInput) {
        for (int i = 0; i < this.warehouses.size(); i++) {
            Warehouse wh = this.warehouses.get(i);
            if (wh.getWhName() == userInput) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find warehouse instance by warehouse name
     *
     * @param userInput
     * @return
     */
    public Warehouse searchWarehouse(String userInput) {
        int viTri = this.searchIdWarehouse(userInput);
        if (viTri == -1) {
            return null;
        } else {
            return this.warehouses.get(viTri);
        }
    }

    /**
     * approximate search warehouse
     *
     * @param userInput
     */
    void approximateSearchWarehouse(String userInput) {
        int dem = 0;
        for (int i = 0; i < this.warehouses.size(); i++) {
            Warehouse wh = this.warehouses.get(i);
            String name = wh.getWhName();
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
     * Show name and address of warehouse
     *
     * @param whId
     * @return
     */
    public String showWarehouse(int whId) {
        Warehouse wh = getWarehouse(whId);

        String chuoi = "";
        chuoi += "Warehouse's name    : " + wh.getWhName() + "\n"
                + "Warehouse's address : " + wh.getWhAddress();

        return chuoi;
    }

    /**
     * Displays all warehouse on Warehouse list
     */
    public void showWarehouseList() {
        int whNo = 1;
        for (int i = 0; i < this.warehouses.size(); i++, whNo++) {
            Warehouse wh = this.warehouses.get(i);

            System.out.println(whNo + ". " + showWarehouse(wh.getWhId()));
        }
    }
}
