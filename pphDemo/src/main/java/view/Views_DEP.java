package view;

import org.pph.pojo.myPackage;

import java.util.Scanner;

public class Views_DEP {
    // Scanner for User Input
    private Scanner input = new Scanner(System.in);

    // Function to welcome
    public void welcome() {
        System.out.println("--- Welcome to myPackage House ---\n");
    }

    // Function to bye
    public void bye() {
        System.out.println("--- See You Next Time! ---\n");
    }

    /**
     * Menu for User (Insert)
     * @return The mode users choose.
     */
    public int menu(){
        System.out.println(
                "Please Follow the Instruction and Select the Mode: \n" +
                        "   1 --> User mode\n" +
                        "   2 --> Courier mode\n" +
                        "   0 --> Exit\n" +
                        "Enter: ");

        // Catch User Input
        String mode = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(mode);
        } catch (NumberFormatException e) {
            System.out.println("menu Number Format Error!\n");
        }

        // Check User Input
        if (num < 0 || num > 2) {
            System.out.println("Please choose the correct mode.\n");
            return menu();
        }
        return num;
    }

    /**
     * Menu for User (Unlock)
     * @return the code users enter.
     */
    public int uMenu(){
        System.out.println("Please Enter your myPackage Code: ");

        // Catch User Input
        String code = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            System.out.println("uMenu Number Format Error!\n");
        }

        // Check User Input
        if (num < 100000 || num > 999999) {
            System.out.println("Please recheck your code.\n");
            return uMenu();
        }
        return num;
    }

    /**
     * Menu for Courier (Lock)
     * @return the code couriers enter.
     */
    public int cMenu(){
        System.out.println("Please choose your action:");
        System.out.println("1. Insert package");
        System.out.println("2. Modify package");
        System.out.println("3. Delete package");
        System.out.println("4. Show all package");
        System.out.println("0. Exit");
        String action = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(action);
        } catch (NumberFormatException e) {
            System.out.println("cMenu Number Format Error!");
        }
        if (num < 0 || num > 4) {
            System.out.println("Please choose the correct action.");
            return cMenu();
        }
        return num;

    }

    /**
     * Function for Insert
     * @return the myPackage with package number and its company.
     */
    public myPackage insert(){
        System.out.println("Enter Your myPackage Information");
        System.out.print("Please Enter myPackage Number:");
        String number = input.nextLine();
        System.out.print("Please Enter myPackage Company:");
        String company = input.nextLine();
        myPackage p = new myPackage();
        p.setNumber(number);
        p.setCompany(company);
        return p;
    }

    // Function for finding a package by its number
    public String findByNumber(){
        System.out.println("To find your package, please enter your package Number: ");
        String num = input.nextLine();
        return num;
    }

    // Function for printing a package
    public void printPackage(myPackage p) {
        System.out.println(
                "--- myPackage Information ---\n"+
                        "myPackage Number: "+p.getNumber()+
                        "\nmyPackage Company: "+p.getCompany()
        );
    }

    public void printNull(){
        System.out.println("myPackage not existed. Please check the info you entered");
    }

    /**
     * Update the information of a package
     * @return the updated package
     */
    public myPackage update(myPackage p){
        System.out.println("Please Enter New myPackage Number: ");
        String number = input.nextLine();
        System.out.println("\nPlease Enter New myPackage Company: ");
        String company = input.nextLine();
        p.setNumber(number);
        p.setCompany(company);
        return p;
    }

    /**
     * Function for deleting packages
     * @return 1: Delete, 2: Cancel
     */
    public int delete(){
        // Print delete message
        System.out.println(
                "Make Sure You Want to Delete! \n"+
                        "1. Delete\n"+
                        "2. Cancel"
        );

        // Catch User Input
        String del = input.nextLine();
        int num = -1;
        try {
            num = Integer.parseInt(del);
        } catch (NumberFormatException e) {
            System.out.println("delete Number Format Error!\n");
        }

        // Check User Input
        if (num < 1 || num > 2) {
            System.out.println("Please Enter Valid Code");
            return delete();
        }
        return num;
    }

    // Function for printing all package information
    public void printAll(myPackage[][] myPackages){
        int count = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(myPackages[i][j] != null){
                    count++;
                    System.out.print((i+1) + " row, " + (j+1) + " column, ");
                    printPackage(myPackages[i][j]);
                }
            }
        }
        if(count == 0){
            System.out.println("No package info");
        }
    }

    public void packageExist(){
        System.out.println("myPackage already existed. Please check the package number");
    }

    public void success(){
        System.out.println("Operation Success");
    }
}