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
public class BrandManagement {

    private String B_FILE;                  //The URL of data file that stores all brand
    private int numberOfBrand;              //Number of questions that stored in data file
    private ArrayList<Brand> brands;         //All instances of questions
    private NationManagement nm;            //Instance of AnswerManagement

    /**
     * Creates instance for warehouse management
     *
     * @param B_FILE
     * @param nm
     * @throws BrandException
     */
    public BrandManagement(String B_FILE, NationManagement nm) throws BrandException {
        if (B_FILE.equals("")) {
            throw new BrandException("The URL of nation data file can't be empty!");
        } else {
            //Inits the URL of data file thats stores brand bank
            this.B_FILE = B_FILE;

            //Creates empty brand bank
            this.brands = new ArrayList<>();

            //So, the number of brand is 0
            this.numberOfBrand = 0;

            //Inits the nation management
            this.nm = nm;
        }
    }

    /**
     * Loads data of questions from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws ProjectAsm.BrandException
     */
    public void loadBrands() throws IOException, BrandException {
        File bFile = new File(B_FILE);

        //Checks is file created
        if (!bFile.exists()) {
            bFile.createNewFile(); //If not, creates new file
            System.out.println(
                    //"\n--------------------" + 
                    "The data file questions.txt is not exits. "
                    + "Creating new data file questions.txt..."
                    + "Done!");

            this.numberOfBrand = 0; //New data file with the number of brand is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print(
                    //"\n--------------------" + 
                    "\nThe data file brand.txt is found. "
                    + "Data of brand is loading...");

            //Loads text file into buffer
            //BufferedReader br = new BufferedReader(new FileReader(B_FILE));
            try (BufferedReader br = new BufferedReader(new FileReader(B_FILE))) {
                String line, bId, bName, nationId;

                //Reads number of answers
                line = br.readLine();
                this.numberOfBrand = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfBrand; i++) {
                    //Reads answer's information
                    bId = br.readLine();
                    bName = br.readLine();
                    nationId = br.readLine();

                    //Create new instance of Answer and adds to data
                    this.brands.add(new Brand(Integer.parseInt(bId), bName, Integer.parseInt(nationId)));
                }
            }
            /*finally {
                br.close();
            }*/
            System.out.println("Done! [" + this.numberOfBrand + " brands]");
        }
    }

    /**
     * Gets number of brand
     *
     * @return
     */
    public int getSize() {
        return this.numberOfBrand;
    }

    /**
     * Adds new brand to brand bank
     *
     * @param bName
     * @param nationId
     * @return
     * @throws BrandException
     */
    public int addBrand(String bName, int nationId) throws BrandException {
        this.brands.add(new Brand(++this.numberOfBrand, bName, nationId));
        return this.numberOfBrand;
    }

    /**
     * Finds brand by brand id and return the index of this brand
     *
     * @param bId
     * @return
     */
    public int findBrand(int bId) {
        for (int i = 0; i < this.brands.size(); i++) {
            Brand b = this.brands.get(i);
            if (b.getBId() == bId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the brand instance by brand id
     *
     * @param bId
     * @return
     */
    public Brand getBrand(int bId) {
        int idx = findBrand(bId);
        if (idx == -1) {
            return null;
        } else {
            return this.brands.get(idx);
        }
    }

    /**
     * Saves brand bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveBrand() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(B_FILE), false);
        try {
            System.out.print(
                    //"\n--------------------" + 
                    "\nBrand is saving into data file brand.txt...");

            //Writes number of brand
            fw.append(String.valueOf(this.numberOfBrand) + "\n");

            for (int i = 0; i < this.numberOfBrand; i++) {
                //Inits brand's information
                int bId = this.brands.get(i).getBId();
                String bName = this.brands.get(i).getBName();
                int nationId = this.brands.get(i).getNationId();

                //Writes brand's information into data file
                fw.append(String.valueOf(bId) + "\n");
                fw.append(bName + "\n");
                fw.append(String.valueOf(nationId) + "\n");
            }
        } finally {
            //Saves data file (from RAM to HDD)
            fw.close();
            System.out.println("Done! [" + this.numberOfBrand + " brands]");
        }
    }

    /**
     * Gets the brand formatted string that includes brand content and all
     * nation that comes with random mode
     *
     * @param bId
     * @return
     */
    public String showBrand(Brand b) {

        Nation n = nm.getNation(b.getNationId());
        String str = "";
        //str += b.toString();

        str += "Brand's name: " + b.getBName() + "\n";
        str += "        Nation      : " + n.getNationName();
        return str;
    }

    /**
     * Displays all brand of brand list
     */
    public void showBrandList() {
        int bNo = 1;
        for (int i = 0; i < this.brands.size(); i++, bNo++) {
            Brand b = this.brands.get(i);

            System.out.println("    " + bNo + ". " + showBrand(b));
            //System.out.println("\n     ---------------------------------------------------------------");
            System.out.println("\n  ***************************************************************\n");
        }
    }

    /**
     * Get brand and nation by brand id
     *
     * @param bId
     * @return
     */
    public String getBrandAndNation(int bId) {
        Brand bn = getBrand(bId);
        Nation n = nm.getNation(bId);
        String chuoi = "";
        //chuoi += bn.toString();

        chuoi += "  " + bId + ". " + bn.getBName() + "\n"
                + "  " + bId + ". " + n.getNationName();
        return chuoi;
    }

    /**
     * approximate search brand
     *
     * @param userInput
     */
    void approximateSearchBrand(String userInput) {
        int dem = 0;
        int bNo = 0;
        for (int i = 0; i < this.brands.size(); i++) {
            Brand b = this.brands.get(i);
            String name = b.getBName();
            if (name.toLowerCase().indexOf(userInput) != -1) {
                ++bNo;
                System.out.println("    " + bNo + ". " + name);
                System.out.println("\n  -------------------------\n");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("------------------ NOT FOUND! --------------------");
        }
    }
}
