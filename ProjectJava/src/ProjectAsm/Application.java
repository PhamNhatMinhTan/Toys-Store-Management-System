package ProjectAsm;

import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Minh Tan
 */
public class Application {

    private static WarehouseManagement whm;
    private static ShopManagement sm;
    private static ProductManagement pm;
    private static BrandManagement bm;
    private static NationManagement nm;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, ProductException, WarehouseException, ShopException, BrandException, NationException {

        try {
            //load warehouse data
            whm = new WarehouseManagement("src/data/warehouse.txt");
            whm.loadWarehouse();

            //Load Nation data
            nm = new NationManagement("src/data/nation.txt");
            nm.loadNation();

            //Load Brand data
            bm = new BrandManagement("src/data/brand.txt", nm);
            bm.loadBrands();

            //Load Product
            pm = new ProductManagement("src/data/product.txt", bm, nm);
            pm.loadProduct();

            //Load Shop data
            sm = new ShopManagement("src/data/shop.txt", whm, pm);
            sm.loadShops();

            //Create scanner
            Scanner cin = new Scanner(System.in);

            //declare var for user choose menu
            int choose, choiceAdd, choiceSearch, choiceShow;
            String userEnter;

            System.out.println("\n########## WELCOME TO MY PROGRAM ###########");
            //User enter name of shop
            choose = 0;
            do {
                System.out.println("\n#-----------# MENU APPLICATION #------------#");
                System.out.println("#   1. Add data information for shop.       #");
                System.out.println("#   2. Search data information of shop.     #");
                System.out.println("#   3. Show shop information.               #");
                System.out.println("#   4. Exit.                                #");
                System.out.println("#-------------------------------------------#\n");

                //
                System.out.println("Please choose function you want to use: ");
                try {
                    choose = cin.nextInt();
                    cin.nextLine();

                    userEnter = "";

                    switch (choose) {
                        //User choose add data for shop
                        case 1:
                            choiceAdd = 0;
                            do {
                                try {
                                    //Menu of function add data for shop
                                    System.out.println("#------# ADD DATA INFORMATION SHOP #-------#");
                                    System.out.println("#        1. Add branch (shop).             #");
                                    System.out.println("#        2. Add warehouse.                 #");
                                    System.out.println("#        3. Add product.                   #");
                                    System.out.println("#        4. Add brand                      #");
                                    System.out.println("#        5. Add nation(origin)             #");
                                    System.out.println("#------------------------------------------#");

                                    //User enter the choice
                                    System.out.println("\nPlease enter your choice: ");
                                    choiceAdd = cin.nextInt();
                                    cin.nextLine();

                                    //The function to add data for shop
                                    switch (choiceAdd) {
                                        case 1:
                                            System.out.println("\n  #########################################");
                                            System.out.println("  #           ADD SHOP (BRANCH)           #");
                                            System.out.println("  #########################################\n");
                                            String shName = "";
                                            String shAddress = "";

                                            //This function will be run over and over again when user enter "Yes"
                                            do {
                                                //Gets the name and address of branch
                                                do {
                                                    System.out.println("Please enter the name of new branch (shop): ");
                                                    shName = cin.nextLine();

                                                    System.out.println("Please enter the address of new branch (shop): ");
                                                    shAddress = cin.nextLine();

                                                    //check name of branch
                                                    if (shName.equals("") || shAddress.equals("")) {
                                                        System.out.println("Name or address of branch (shop) can't be empty!");
                                                    }
                                                } while (shName.equals("") || shAddress.equals(""));

                                                //Create new branch and get shop's ID
                                                int shId = sm.addShop(shName, shAddress);

                                                System.out.println("New branch have been created!");

                                                //Ask user that they want to add more branch
                                                do {
                                                    System.out.println("\nDo you want to add more branch? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 2:
                                            System.out.println("\n  #########################################");
                                            System.out.println("  #             ADD WAREHOUSE             #");
                                            System.out.println("  #########################################\n");
                                            String whName = "";
                                            String whAddress = "";

                                            //This function will be run over and over again when user enter "Yes"
                                            do {
                                                //Gets the name and address of warehouse
                                                do {
                                                    System.out.println("Please enter the name of warehouse: ");
                                                    whName = cin.nextLine();

                                                    System.out.println("Please enter address of warehouse: ");
                                                    whAddress = cin.nextLine();

                                                    //check input of whName and whAddress
                                                    if (whName.equals("") || whAddress.equals("")) {
                                                        System.out.println("Error: Name or address of warehouse can't be empty!");
                                                    }
                                                } while (whName.equals("") || whAddress.equals(""));

                                                //Add warehouse
                                                int whId = whm.addWarehouse(whName, whAddress);
                                                System.out.println("New warehouse have been created!\n");

                                                //Ask user that they want to add more warehouse
                                                do {
                                                    System.out.println("\nDo you want to add more warehouse? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 3:
                                            System.out.println("\n   #########################################");
                                            System.out.println("   #              ADD PRODUCT              #");
                                            System.out.println("   #########################################\n");
                                            //declare var name, brand, size, price, material, ages and quantity of product
                                            String proName = "";
                                            int bId;
                                            String size;
                                            float price;
                                            String material = "";
                                            int ages;
                                            int quantity;

                                            //This function will be run over and over again when user enter "Yes"
                                            do {
                                                //Get name for product
                                                do {
                                                    System.out.println("Please enter name of product: ");
                                                    proName = cin.nextLine();

                                                    if (proName.equals("")) {
                                                        System.out.println("Error: Name of product can't be empty!");
                                                    }
                                                } while (proName.equals(""));

                                                //Get brand of product
                                                bId = 0;
                                                do {
                                                    System.out.println("Please enter brand ID of product: ");
                                                    try {
                                                        bId = cin.nextInt();
                                                        cin.nextLine();

                                                        if (bId < 0) {
                                                            System.out.println("Error: Brand ID must be greater than 0!");
                                                        }
                                                    } catch (Exception ex) {
                                                        System.out.println("Error: Please enter a integer number!");
                                                        bId = -1;
                                                        cin.nextLine();
                                                    }
                                                } while (bId < 0);

                                                //Get size of product
                                                do {
                                                    System.out.println("Please enter size(Small - Medium - Large) of product: ");
                                                    size = cin.nextLine();

                                                    if (!(size.equals("Small") || size.equals("Medium") || size.equals("Large"))) {
                                                        System.out.println("Size of product must be 'Small', 'Medium' or 'Large'!");
                                                    }
                                                } while (!(size.equals("Small") || size.equals("Medium") || size.equals("Large")));

                                                //Get price of product
                                                do {
                                                    System.out.println("Please enter price of product: ");
                                                    price = cin.nextFloat();
                                                    cin.nextLine();

                                                    if (price < 0) {
                                                        System.out.println("Error: Price of product must be positive number!");
                                                    }
                                                } while (price < 0);

                                                //Get name for product
                                                do {
                                                    System.out.println("Please enter material of product: ");
                                                    material = cin.nextLine();

                                                    if (material.equals("")) {
                                                        System.out.println("Error: Material of product can't be empty!");
                                                    }
                                                } while (material.equals(""));

                                                //Get ages of product
                                                ages = 0;
                                                do {
                                                    System.out.println("Please enter the user age appropriate for the product: ");
                                                    try {
                                                        ages = cin.nextInt();
                                                        cin.nextLine();
                                                        if (ages < 1 || ages > 170) {
                                                            System.out.println("Error: The age entered must be 01 - 170 years old!");
                                                        }
                                                    } catch (Exception ex) {
                                                        System.out.println("Error: Please enter a integer number!");
                                                        ages = -1;
                                                        cin.nextLine();
                                                    }
                                                } while (ages < 1 || ages > 170);

                                                //Get quantity of product
                                                quantity = 0;
                                                do {
                                                    System.out.println("Please enter the quantity of product: ");
                                                    try {
                                                        quantity = cin.nextInt();
                                                        cin.nextLine();

                                                        if (quantity < 0) {
                                                            System.out.println("Error: Quantity of product must be positive number!");
                                                        }
                                                    } catch (Exception ex) {
                                                        System.out.println("Error: Please enter a integer number!");
                                                        quantity = -1;
                                                        cin.nextLine();
                                                    }
                                                } while (quantity < 0);

                                                int proId = pm.addProduct(proName, bId, size, price, material, ages, quantity);
                                                System.out.println("New product has been add into shop!");

                                                //Ask user that they want to add more product
                                                do {
                                                    System.out.println("\nDo you want to add more product? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 4:
                                            System.out.println("\n   #########################################");
                                            System.out.println("   #               ADD BRANCH              #");
                                            System.out.println("   #########################################\n");
                                            String bName;
                                            int nationId;

                                            //This function will be run over and over again when user enter "Yes"
                                            do {
                                                //get name of brand
                                                do {
                                                    System.out.println("Please enter name of brand: ");
                                                    bName = cin.nextLine();

                                                    if (bName.equals("")) {
                                                        System.out.println("Name of brand can't be empty!");
                                                    }
                                                } while (bName.equals(""));

                                                //get Id's nation of brand
                                                nationId = 0;
                                                do {
                                                    System.out.println("Please enter Id's nation of brand: ");
                                                    try {
                                                        nationId = cin.nextInt();
                                                        cin.nextLine();

                                                        if (nationId < 0) {
                                                            System.out.println("Error: Nation ID must be greater than 0!");
                                                        }
                                                    } catch (Exception ex) {
                                                        System.out.println("Error: Please enter a integer number!");
                                                        nationId = -1;
                                                        cin.nextLine();
                                                    }
                                                } while (nationId < 0);

                                                bId = bm.addBrand(bName, nationId);
                                                System.out.println("New brand has been add into list brand!");

                                                //Ask user that they want to add more brand
                                                do {
                                                    System.out.println("\nDo you want to add more brand? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));

                                            break;
                                        case 5:
                                            System.out.println("\n   #########################################");
                                            System.out.println("   #          ADD NATION (ORIGIN)          #");
                                            System.out.println("   #########################################\n");
                                            String nationName;

                                            //This function will be run over and over again when user enter "Yes"
                                            do {
                                                //Get naame of nation
                                                do {
                                                    System.out.println("Please enter name of nation: ");
                                                    nationName = cin.nextLine();

                                                    //check nation Name
                                                    if (nationName.equals("")) {
                                                        System.out.println("Error: Name of nation can't be empty!");
                                                    }

                                                } while (nationName.equals(""));

                                                System.out.println("Nation has been add into list nation!");

                                                nationId = nm.addNation(nationName);

                                                //Ask user that they want to add more nation
                                                do {
                                                    System.out.println("\nDo you want to add more nation? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;

                                        default:
                                            System.out.println("Error: You must type from 1 to 5!");
                                    }
                                    //Ask user that they want to use this function again
                                    do {
                                        System.out.println("\nDo you want to add anything else? (Yes/No) ");
                                        userEnter = cin.nextLine();

                                        //Check value that user enter
                                        //This part isn't case sensitive or abbreviated
                                        if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                || userEnter.equals("yes") || userEnter.equals("no")
                                                || userEnter.equals("Y") || userEnter.equals("N")
                                                || userEnter.equals("y") || userEnter.equals("n")))) {
                                            System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                        }
                                    } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                            || userEnter.equals("YEs") || userEnter.equals("NO")
                                            || userEnter.equals("YeS") || userEnter.equals("nO")
                                            || userEnter.equals("YES") || userEnter.equals("yES")
                                            || userEnter.equals("yes") || userEnter.equals("no")
                                            || userEnter.equals("Y") || userEnter.equals("N")
                                            || userEnter.equals("y") || userEnter.equals("n"))));
                                } catch (Exception ex) {
                                    System.out.println("Error: Not valid! You must be enter a integer number and from 1 to 5!\n");
                                    choiceAdd = -1;
                                    cin.nextLine();
                                }
                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                    || userEnter.equals("Y") || userEnter.equals("y") || choiceAdd == -1);
                            break;

                        case 2:
                            choiceSearch = 0;
                            //run agian this functions
                            do {
                                try {
                                    //Show menu of function Search
                                    System.out.println("\n#-----# SEARCH DATA INFORMATION SHOP #-----#");
                                    System.out.println("#       1. Search branch (shop).           #");
                                    System.out.println("#       2. Search warehouse.               #");
                                    System.out.println("#       3. Search product.                 #");
                                    System.out.println("#       4. Search brand.                   #");
                                    System.out.println("#       5. Search nation(origin).          #");
                                    System.out.println("#       6. Search advance.                 #");
                                    System.out.println("#------------------------------------------#");

                                    //User enter the choice
                                    System.out.println("\nPlease enter your choice: ");
                                    choiceSearch = cin.nextInt();
                                    cin.nextLine();

                                    //The function to add data for shop
                                    switch (choiceSearch) {
                                        case 1:
                                            System.out.println("\n       #########################################");
                                            System.out.println("       #             SEARCH BRANCH             #");
                                            System.out.println("       #########################################\n");
                                            System.out.println("So sorry! This function is in the process of development!");
                                            System.out.println("Please come back next time!");
                                            break;
                                        case 2:
                                            System.out.println("\n       #########################################");
                                            System.out.println("       #             SEARCH WAREHOUS           #");
                                            System.out.println("       #########################################\n");
                                            System.out.println("So sorry! This function is in the process of development!");
                                            System.out.println("Please come back next time!");
                                            break;
                                        case 3:
                                            do {
                                                System.out.println("\n       #########################################");
                                                System.out.println("       #             SEARCH PRODUCT            #");
                                                System.out.println("       #########################################\n");
                                                System.out.println("Please enter product that you want to search: ");
                                                userEnter = cin.nextLine();

                                                //Display result
                                                System.out.println("\n             ##############################");
                                                System.out.println("             #           RESULT           #");
                                                System.out.println("             ##############################\n");
                                                pm.approximateSearchProduct(userEnter);
                                                //Ask user that they want to add more nation
                                                do {
                                                    System.out.println("\nDo you want to search again? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 4:
                                            do {
                                                System.out.println("\n    #########################################");
                                                System.out.println("    #              SEARCH BRAND             #");
                                                System.out.println("    #########################################\n");
                                                System.out.println("Please enter brand that you want to search: ");
                                                userEnter = cin.nextLine();
                                                //Display result
                                                System.out.println("\n       ##############################");
                                                System.out.println("       #           RESULT           #");
                                                System.out.println("       ##############################\n");
                                                bm.approximateSearchBrand(userEnter);
                                                //Ask user that they want to add more nation
                                                do {
                                                    System.out.println("\nDo you want to search again? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 5:
                                            do {
                                                System.out.println("\n     #########################################");
                                                System.out.println("     #        SEARCH NATION (ORIGIN )        #");
                                                System.out.println("     #########################################\n");
                                                System.out.println("Please enter nation (origin) that you want to search: ");
                                                userEnter = cin.nextLine();
                                                //Display result
                                                System.out.println("\n         ##############################");
                                                System.out.println("         #           RESULT           #");
                                                System.out.println("         ##############################\n");
                                                nm.approximateSearchNation(userEnter);
                                                //Ask user that they want to search origin again
                                                do {
                                                    System.out.println("\nDo you want to search again? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        case 6:
                                            int searchAdvance;
                                            do {
                                                System.out.println("\n         #########################################");
                                                System.out.println("         #             SEARCH ADVANCE            #");
                                                System.out.println("         #########################################\n");
                                                System.out.print("Enter product's name (Can be empty if you don't want to search by product's name): ");
                                                String userEnterProduct = cin.nextLine();
                                                System.out.print("Enter brand's name (Can be empty if you don't want to search by brand's name): ");
                                                String userEnterBrand = cin.nextLine();
                                                System.out.print("Enter origin's name (Can be empty if you don't want to search by origin's name): ");
                                                String userEnterOrigin = cin.nextLine();

                                                //ask user search or clear text that they entered
                                                do {
                                                    System.out.println("\nDo you want to search now? Or clear all words that you just entered?");
                                                    System.out.println("1. Search.");
                                                    System.out.println("2. Clear.");
                                                    System.out.println("Please enter your choice: ");
                                                    try {
                                                        searchAdvance = cin.nextInt();
                                                        cin.nextLine();
                                                        if (searchAdvance != 1 && searchAdvance != 2) {
                                                            System.out.println("Error: Please choose function Search(1) or Clear(2)!");
                                                        }
                                                    } catch (Exception ex) {
                                                        System.out.println("Error: Not valid! You must be enter a integer number (1 or 2)!");
                                                        searchAdvance = -1;
                                                        cin.nextLine();
                                                    }
                                                } while ((searchAdvance != 1 && searchAdvance != 2) || searchAdvance == -1);
                                                switch (searchAdvance) {
                                                    case 1:
                                                        System.out.println("\n\n           ##############################");
                                                        System.out.println("           #           RESULT           #");
                                                        System.out.println("           ##############################\n");
                                                        pm.searchAdvance(userEnterProduct, userEnterBrand, userEnterOrigin);
                                                        break;
                                                    case 2:
                                                        userEnterProduct = "";
                                                        userEnterBrand = "";
                                                        userEnterOrigin = "";
                                                        System.out.println("The words you just entered have been cleared.");
                                                        break;
                                                    default:
                                                        System.out.println("Error: You must be type 1 or 2!");
                                                }
                                                //Ask user that they want to search advance again
                                                do {
                                                    System.out.println("\nDo you want to search again? (Yes/No) ");
                                                    userEnter = cin.nextLine();

                                                    //Check value that user enter
                                                    //This part isn't case sensitive or abbreviated
                                                    if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                            || userEnter.equals("yes") || userEnter.equals("no")
                                                            || userEnter.equals("Y") || userEnter.equals("N")
                                                            || userEnter.equals("y") || userEnter.equals("n")))) {
                                                        System.out.println("Error: Not valid! You must be type 'Yes' or 'No'!");
                                                    }
                                                } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                        || userEnter.equals("YEs") || userEnter.equals("NO")
                                                        || userEnter.equals("YeS") || userEnter.equals("nO")
                                                        || userEnter.equals("YES") || userEnter.equals("yES")
                                                        || userEnter.equals("yes") || userEnter.equals("no")
                                                        || userEnter.equals("Y") || userEnter.equals("N")
                                                        || userEnter.equals("y") || userEnter.equals("n"))));
                                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                                    || userEnter.equals("Y") || userEnter.equals("y"));
                                            break;
                                        default:
                                            System.out.println("Error: You must type from 1 to 6!");
                                    }
                                    //Ask user that they want to use this function again
                                    do {
                                        System.out.println("\nDo you want to search anything else? (Yes/No) ");
                                        userEnter = cin.nextLine();

                                        //Check value that user enter
                                        //This part isn't case sensitive or abbreviated
                                        if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                || userEnter.equals("yes") || userEnter.equals("no")
                                                || userEnter.equals("Y") || userEnter.equals("N")
                                                || userEnter.equals("y") || userEnter.equals("n")))) {
                                            System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                        }
                                    } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                            || userEnter.equals("YEs") || userEnter.equals("NO")
                                            || userEnter.equals("YeS") || userEnter.equals("nO")
                                            || userEnter.equals("YES") || userEnter.equals("yES")
                                            || userEnter.equals("yes") || userEnter.equals("no")
                                            || userEnter.equals("Y") || userEnter.equals("N")
                                            || userEnter.equals("y") || userEnter.equals("n"))));
                                } catch (Exception ex) {
                                    System.out.println("Error: Not valid! You must be enter a integer number and from 1 to 6!\n");
                                    choiceSearch = -1;
                                    cin.nextLine();
                                }
                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                    || userEnter.equals("Y") || userEnter.equals("y") || choiceSearch == -1);
                            break;

                        case 3:
                            choiceShow = 0;
                            do {
                                try {
                                    //Show menu of function Show data of shop
                                    System.out.println("\n#------# SHOW DATA INFORMATION SHOP #------#");
                                    System.out.println("#        1. Show branch (shop).            #");
                                    System.out.println("#        2. Show warehouse.                #");
                                    System.out.println("#        3. Show product.                  #");
                                    System.out.println("#        4. Show brand.                    #");
                                    System.out.println("#        5. Show nation(origin).           #");
                                    System.out.println("#------------------------------------------#");

                                    //User enter the choice
                                    System.out.println("\nPlease enter your choice: ");
                                    choiceShow = cin.nextInt();
                                    cin.nextLine();

                                    //The function to add data for shop
                                    switch (choiceShow) {
                                        case 1:
                                            System.out.println("\n            #########################################");
                                            System.out.println("            #          SHOW SHOP (BRANCH)           #");
                                            System.out.println("            #########################################\n");
                                            sm.showAllShop();
                                            break;
                                        case 2:
                                            System.out.println("\n            #########################################");
                                            System.out.println("            #              SHOW WAREHOUSE           #");
                                            System.out.println("            #########################################\n");
                                            whm.showWarehouseList();
                                            break;
                                        case 3:
                                            System.out.println("\n            #########################################");
                                            System.out.println("            #               SHOW PRODUCT            #");
                                            System.out.println("            #########################################\n");
                                            pm.showProductList();

                                            break;
                                        case 4:
                                            System.out.println("\n            #########################################");
                                            System.out.println("            #                SHOW BRAND             #");
                                            System.out.println("            #########################################\n");
                                            bm.showBrandList();
                                            break;
                                        case 5:
                                            System.out.println("\n            #########################################");
                                            System.out.println("            #         SHOW NATION (ORIGIN)          #");
                                            System.out.println("            #########################################\n");
                                            //System.out.println("------- SHOW NATION (ORIGIN) -------");
                                            nm.showNationList();
                                            break;
                                        default:
                                            System.out.println("Error: You must type from 1 to 5!");
                                            cin.nextLine();
                                    }
                                    //Ask user that they want to use this function again
                                    do {
                                        System.out.println("\nDo you want to show anything else? (Yes/No) ");
                                        userEnter = cin.nextLine();

                                        //Check value that user enter
                                        //This part isn't case sensitive or abbreviated
                                        if ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                                || userEnter.equals("yes") || userEnter.equals("no")
                                                || userEnter.equals("Y") || userEnter.equals("N")
                                                || userEnter.equals("y") || userEnter.equals("n")))) {
                                            System.out.println("Error:Not valid! You must be type 'Yes' or 'No'!");
                                        }
                                    } while ((!(userEnter.equals("Yes") || userEnter.equals("No")
                                            || userEnter.equals("YEs") || userEnter.equals("NO")
                                            || userEnter.equals("YeS") || userEnter.equals("nO")
                                            || userEnter.equals("YES") || userEnter.equals("yES")
                                            || userEnter.equals("yes") || userEnter.equals("no")
                                            || userEnter.equals("Y") || userEnter.equals("N")
                                            || userEnter.equals("y") || userEnter.equals("n"))));
                                } catch (Exception Ex) {
                                    System.out.println("Error: Not valid! You must be enter a integer number and from 1 to 5!\n");
                                    choiceShow = -1;
                                    cin.nextLine();
                                }

                            } while (userEnter.equals("Yes") || userEnter.equals("yes")
                                    || userEnter.equals("YEs") || userEnter.equals("YeS")
                                    || userEnter.equals("YES") || userEnter.equals("yES")
                                    || userEnter.equals("Y") || userEnter.equals("y") || choiceShow == -1);
                            break;
                        case 4:
                            //Good bye user
                            System.out.println("\n    #---------------------# THANKS FOR USED US SOFTWARE #----------------------#");
                            System.out.println("    #   + If you have a problem or have any questions. Please contact us via:  #");
                            System.out.println("    #   + Phone number: 0704-605-569.                                          #");
                            System.out.println("    #   + Email: TanPNMCE130084@fpt.edu.vn                                     #");
                            System.out.println("    #   + See you again!                                                       #");
                            System.out.println("    #--------------------------------------------------------------------------#");

                            break;
                        default:
                            //If user don't choose from 1 to 4
                            System.out.println("Error: You must type from 1 to 4!");
                    }
                } catch (Exception ex) {
                    System.out.println("Error: Not valid! You must be enter a integer number and from 1 to 4!");
                    choose = -1;
                    cin.nextLine();
                }
            } while (choose != 4 || choose == -1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //Saving data of warehouse
                whm.saveWarehouse();
            } catch (Exception e) {
                System.out.println("Exception: Can't save data information of warehouse!");
            }

            try {
                //Saving data of shop
                sm.saveShop();
            } catch (Exception e) {
                System.out.println("Exception: Can't save data information of shop!");
            }

            try {
                //Saving data of produtct
                pm.saveProduct();
            } catch (Exception e) {
                System.out.println("Exception: Can't save data information of product!");
            }

            try {
                //saving data of brand
                bm.saveBrand();
            } catch (Exception e) {
                System.out.println("Exception: Can't save data information of brand!");
            }

            try {
                //saving data of nation (origin)
                nm.saveNation();
            } catch (Exception e) {
                System.out.println("Exception: Can't save data information of nation (origin)!");
            }
        }
    }

}
