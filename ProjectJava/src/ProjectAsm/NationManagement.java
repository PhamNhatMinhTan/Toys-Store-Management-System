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
public class NationManagement {

    private String N_FILE;              //The URL of data file that stores all answers
    private int numberOfNation;         //Number of answers that stored in data file
    private ArrayList<Nation> nations;  //All instances of answers

    /**
     * Creates instance for nation management
     *
     * @param N_FILE
     * @throws ProjectAsm.NationException
     */
    public NationManagement(String N_FILE) throws NationException {
        if (N_FILE.equals("")) {
            throw new NationException("The URL of answer data file can't be empty!");
        } else {
            //Inits the URL of data file thats stores nation list
            this.N_FILE = N_FILE;

            //Creates empty nation list
            this.nations = new ArrayList<>();

            //So, the number of answer is 0
            this.numberOfNation = 0;
        }
    }

    /**
     * Loads data of nation from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws NationException
     */
    public void loadNation() throws IOException, NationException {
        File nFile = new File(N_FILE);

        //Checks is file created
        if (!nFile.exists()) {
            nFile.createNewFile(); //If not, creates new file
            System.out.println(
                    //"\n--------------------\n" + 
                    "The data file nations.txt is not exits. "
                    + "Creating new data file nations.txt... "
                    + "Done!");
            this.numberOfNation = 0; //New data file with the number of answer is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print(
                    //"\n--------------------" + 
                    "\nThe data file nations.txt is found. "
                    + "Data of nations is loading...");

            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(N_FILE))) {
                String line, nationId, nationName;

                //Reads number of nation
                line = br.readLine();
                this.numberOfNation = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfNation; i++) {
                    //Reads nation's information
                    nationId = br.readLine();
                    nationName = br.readLine();

                    //Create new instance of Answer and adds to answer bank
                    this.nations.add(new Nation(Integer.parseInt(nationId), nationName));
                }
            }
            System.out.println("Done! We have total [" + this.numberOfNation + " nations]");
        }
    }

    /**
     * Add new nation into nation list
     *
     * @param nationName
     * @return
     * @throws NationException
     */
    public int addNation(String nationName) throws NationException {
        this.nations.add(new Nation(++this.numberOfNation, nationName));
        return this.numberOfNation;
    }

    /**
     * Finds nation by nation id and return the index of this nation
     *
     * @param nationId
     * @return
     */
    public int findNation(int nationId) {
        for (int i = 0; i < this.nations.size(); i++) {
            Nation n = this.nations.get(i);
            if (n.getNationId() == nationId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the nation instance by nation id
     *
     * @param nationId
     * @return
     */
    public Nation getNation(int nationId) {
        int idx = findNation(nationId);
        if (idx == -1) {
            return null;
        } else {
            return this.nations.get(idx);
        }
    }

    /**
     * Saves nation (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveNation() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(N_FILE), false);
        try {
            System.out.print(
                    //"\n--------------------" + 
                    "\nNation is saving into data file nation.txt...");

            //Writes number of nation
            fw.append(String.valueOf(this.numberOfNation) + "\n");

            for (int i = 0; i < this.numberOfNation; i++) {
                //Inits answer's information
                int nationId = this.nations.get(i).getNationId();
                String nationName = this.nations.get(i).getNationName();

                //Writes nation's information into data file
                fw.append(String.valueOf(nationId) + "\n");
                fw.append(nationName + "\n");
            }
        } finally {
            //Saves data file (from RAM into HDD)
            fw.close();
            System.out.println("Done! Saved [" + this.numberOfNation + " nations]");
        }
    }

    /**
     * Approximate search nation
     *
     * @param userInput
     */
    void approximateSearchNation(String userInput) {
        int dem = 0;
        int nNo = 0;
        for (int i = 0; i < this.nations.size(); i++) {
            Nation n = this.nations.get(i);
            String name = n.getNationName();
            if (name.toLowerCase().indexOf(userInput) != -1) {
                ++nNo;
                System.out.println("    " + nNo + ". " + name);
                System.out.println("\n  -----------------------------------------------\n");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("------------------ NOT FOUND! -------------------");
        }
    }

    /**
     * Show nation name when search name of nation
     *
     * @param nationId
     * @return
     */
    public String showNation(int nationId) {
        Nation n = getNation(nationId);

        String chuoi = "";

        chuoi += n.getNationName();

        return chuoi;
    }

    /**
     * Displays all nation in Nation list
     */
    public void showNationList() {
        int nNo = 1;
        for (int i = 0; i < this.nations.size(); i++, nNo++) {
            Nation n = this.nations.get(i);

            System.out.println("        " + nNo + ". " + showNation(n.getNationId()));
            System.out.println("\n     ***************************************************\n");
        }
    }

    /**
     * Gets all nation name
     *
     * @return
     */
    public ArrayList<Nation> getNations() {
        ArrayList<Nation> nList = new ArrayList<>();

        for (int i = 0; i < this.nations.size(); i++) {
            Nation n = this.nations.get(i);
            nList.add(n);
        }

        return nList;
    }
}
