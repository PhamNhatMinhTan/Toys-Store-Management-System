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
public class ProductManagement {

    private String P_FILE;                  //Declare file data product
    private int numberOfProduct;            //Declare the number of product of shop
    private BrandManagement bm;             //Instance of BrandManagement
    private NationManagement nm;            //Instance of NationManagement
    private ArrayList<Product> products;    //Create array list of product

    /**
     * Create instance for product management
     *
     * @param P_FILE
     * @param bm
     * @param nm
     * @throws ProductException
     */
    public ProductManagement(String P_FILE, BrandManagement bm, NationManagement nm) throws ProductException {
        if (P_FILE.equals("")) {
            throw new ProductException("The URL of product data file can't be empty!");
        } else {
            this.P_FILE = P_FILE;

            //Create empty list to store product
            this.products = new ArrayList<Product>();

            //So the number of product 
            this.numberOfProduct = 0;

            //Inits brand management
            this.bm = bm;

            //Inits nation management
            this.nm = nm;
        }
    }

    /**
     * Load data file of product and store it into ArrayList
     *
     * @throws IOException
     * @throws ProductException
     */
    public void loadProduct() throws IOException, ProductException {
        File pFile = new File(P_FILE);

        //Check is file created
        if (!pFile.exists()) {
            pFile.createNewFile();      //If not, create new file
            System.out.println(//"------------------------\n" + 
                    "File product.txt is not exist!"
                    + "Creating new data file product.txt... "
                    + "Done!");

            //Create number of product
            this.numberOfProduct = 0;
        } else {
            //If file exist, so loading this file
            System.out.println(//"\n----------------------- + 
                    "The data file product.txt is found!"
                    + "Loading data file...");

            //Loads data file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(P_FILE))) {
                String line, proId, proName, bId, size, price, material, ages, quantity;

                //Reads number of product
                line = br.readLine();
                this.numberOfProduct = Integer.parseInt(line);

                for (int i = 0; i < this.numberOfProduct; i++) {
                    //Reads product's information
                    proId = br.readLine();
                    proName = br.readLine();
                    bId = br.readLine();
                    size = br.readLine();
                    price = br.readLine();
                    material = br.readLine();
                    ages = br.readLine();
                    quantity = br.readLine();
                    //shId            = br.readLine();

                    //Creates new instance of product and add to list product of shop
                    this.products.add(new Product(Integer.parseInt(proId), proName, Integer.parseInt(bId),
                            size, Float.parseFloat(price),
                            material, Integer.parseInt(ages),
                            Integer.parseInt(quantity)));
                }
            }
            System.out.println("Done! You have total [" + this.numberOfProduct + " product]");
        }
    }

    /**
     * Add new product into ArrayList
     *
     * @param proName
     * @param bId
     * @param size
     * @param price
     * @param material
     * @param ages
     * @param quantity
     * @return
     * @throws ProductException
     */
    public int addProduct(String proName, int bId, String size, float price,
            String material, int ages, int quantity) throws ProductException {

        this.products.add(new Product(++this.numberOfProduct, proName, bId, size,
                price, material, ages, quantity));

        return this.numberOfProduct;
    }

    /**
     * Find index of product and return index
     *
     * @param proId
     * @return
     */
    public int findProduct(int proId) {
        for (int i = 0; i < this.products.size(); i++) {
            Product p = this.products.get(i);
            if (p.getProId() == proId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find product by index of product ID
     *
     * @param proId
     * @return
     */
    public Product getProduct(int proId) {
        int viTri = findProduct(proId);
        if (viTri == -1) {
            return null;
        } else {
            return this.products.get(viTri);
        }
    }

    /**
     * Save product information into data file
     *
     * @throws IOException
     */
    public void saveProduct() throws IOException {
        //Overwrite data file
        FileWriter fw = new FileWriter(new File(P_FILE), false);

        try {
            System.out.println(//"\n----------------------" + 
                    "\nData of your product is saving into file product.txt...");

            //write number of product
            fw.append(String.valueOf(this.numberOfProduct) + "\n");

            for (int i = 0; i < this.numberOfProduct; i++) {
                //Inits product's information
                int proId = this.products.get(i).getProId();
                String proName = this.products.get(i).getProName();
                int bId = this.products.get(i).getBId();
                String size = this.products.get(i).getSize();
                float price = this.products.get(i).getPrice();
                String material = this.products.get(i).getMaterial();
                int ages = this.products.get(i).getAges();
                int quantity = this.products.get(i).getQuantity();

                //Writes product's information into data file
                fw.append(String.valueOf(proId) + "\n");
                fw.append(proName + "\n");
                fw.append(String.valueOf(bId) + "\n");
                fw.append(String.valueOf(size) + "\n");
                fw.append(String.valueOf(price) + "\n");
                fw.append(material + "\n");
                fw.append(String.valueOf(ages) + "\n");
                fw.append(String.valueOf(quantity) + "\n");
            }
        } finally {
            //Save data files (from RAM into HDD)
            fw.close();
            System.out.println("Done! Saved [" + this.numberOfProduct + "product]");
        }
    }

    /**
     * Find shop by name of shop and return index of shop
     *
     * @param userInput
     * @return
     */
    public int searchIdProduct(String userInput) {
        for (int i = 0; i < this.products.size(); i++) {
            Product p = this.products.get(i);
            if (p.getProName() == userInput) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find product instance by product name
     *
     * @param userInput
     * @return
     */
    public Product searchProduct(String userInput) {
        int viTri = this.searchIdProduct(userInput);
        if (viTri == -1) {
            return null;
        } else {
            return this.products.get(viTri);
        }
    }

    /**
     * Find brand by name brand and return index of brand
     *
     * @param userInput
     * @return
     */
    public int searchIdBrand(int userInput) {
        for (int i = 0; i < this.products.size(); i++) {
            Product p = this.products.get(i);
            if (p.getBId() == userInput) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find Brand instance by brand name
     *
     * @param userInput
     * @return
     */
    public Product searchBrand(int userInput) {
        int viTri = this.searchIdBrand(userInput);
        if (viTri == -1) {
            return null;
        } else {
            return this.products.get(viTri);
        }
    }

    /**
     * approximate search product
     *
     * @param userInput
     */
    void approximateSearchProduct(String userInput) {
        int dem = 0;
        int pNo = 0;
        for (int i = 0; i < this.products.size(); i++) {
            Product p = this.products.get(i);
            String name = p.getProName();
            /*
            String size = p.getSize();
            String material = p.getMaterial();
             */
            Brand b = bm.getBrand(p.getBId());
            Nation n = nm.getNation(b.getNationId());
            //Approximate search name of product
            if (name.toLowerCase().indexOf(userInput) != -1) {
                ++pNo;
                System.out.println("    " + pNo + ". " + name + ": ");
                System.out.println("        + Brand              : " + b.getBName());
                System.out.println("        + Origin             : " + n.getNationName());
                System.out.println("        + Size               : " + p.getSize());
                System.out.println("        + Price              : " + p.getPrice() + "0 VND");
                System.out.println("        + Material           : " + p.getMaterial());
                System.out.println("        + Age appropriate    : " + p.getAges() + " years old or higher");
                System.out.println("        + Quantity remaining : " + p.getQuantity());
                System.out.println("\n  -------------------------------------------------------------------\n");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("-------------- NOT FOUND! ----------------");
        }
    }

    /**
     * Search advance
     *
     * @param enterProduct
     * @param enterBrand
     * @param enterOrigin
     */
    public void searchAdvance(String enterProduct, String enterBrand, String enterOrigin) {
        //add all list from ArrayList products into proList
        ArrayList<Product> proList = new ArrayList<>(this.products);
        //if user not enter product's name
        if (enterProduct.equals("")) {
        } else {
            ArrayList<Product> pList = new ArrayList<>();
            for (int i = 0; i < proList.size(); i++) {
                Product p = proList.get(i);
                String name = p.getProName();
                //approximate search product
                if (name.toLowerCase().indexOf(enterProduct) != -1) {
                    pList.add(p);       //add all roduct into pList
                }
            }
            proList = new ArrayList<>(pList);       //overwrite plist into proList
        }
        if (enterBrand.equals("")) {
        } else {
            ArrayList<Product> brList = new ArrayList<>();
            for (int i = 0; i < proList.size(); i++) {
                Product p = proList.get(i);
                Brand b = bm.getBrand(p.getBId());
                if (b.getBName().toLowerCase().indexOf(enterBrand) != -1) {
                    brList.add(p);      //add product have brand valid into brList
                }
            }
            proList = new ArrayList<>(brList);      //overwrite brList into proList
        }
        if (enterOrigin.equals("")) {
        } else {
            ArrayList<Product> brList = new ArrayList<>();      //create new array list
            for (int i = 0; i < proList.size(); i++) {
                Product p = proList.get(i);
                Nation n = nm.getNation(bm.getBrand(p.getBId()).getNationId());
                if (n.getNationName().toLowerCase().indexOf(enterOrigin) != -1) {
                    brList.add(p);
                }
            }
            proList = new ArrayList<>(brList);
        }
        //show result search
        if (proList.size() == 0) {
            System.out.println("------------------- NOT FOUND! ----------------");
        } else {
            for (int i = 0; i < proList.size(); i++) {
                Product p = proList.get(i);
                System.out.println("    " + (i + 1) + ". " + showProduct(p.getProId()));
                System.out.println("  --------------------------------------------------------------------------------------");
            }
        }
    }

    /**
     * Show product include all information of product
     *
     * @param proId
     * @return
     */
    public String showProduct(int proId) {
        Product p = getProduct(proId);
        //Product pList   = getProduct(proId);
        Brand b = bm.getBrand(p.getBId());
        Nation n = nm.getNation(b.getNationId());
        String chuoi = "";
        //chuoi += p.toString();

        chuoi += p.getProName() + ": \n"
                + "             + Brand              : " + b.getBName() + "\n"
                + "             + Origin             : " + n.getNationName() + "\n"
                + "             + Size               : " + p.getSize() + "\n"
                + "             + Price              : " + p.getPrice() + "0 VND\n"
                + "             + Material           : " + p.getMaterial() + "\n"
                + "             + Age appropriate    : " + p.getAges() + " years old or higher\n"
                + "             + Quantity remaining : " + p.getQuantity() + "\n";
        return chuoi;
    }

    /**
     * Displays all product in product list
     */
    public void showProductList() {
        int pNo = 1;
        //ArrayList<Brand> bList = bm.getBrands();
        //ArrayList<Nation> nList = nm.getNations();
        for (int i = 0; i < this.products.size(); i++, pNo++) {
            Product p = this.products.get(i);

            System.out.println("        " + pNo + ". " + showProduct(p.getProId()));
            System.out.println("\n     *********************************************************\n");
        }
    }
}
