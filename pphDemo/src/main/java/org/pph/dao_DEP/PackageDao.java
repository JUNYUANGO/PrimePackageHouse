package org.pph.dao_DEP;

import org.pph.pojo.myPackage;
import java.util.Random;

public class PackageDao {

    //Create myPackage List
    private myPackage[][] myPackageList = new myPackage[10][];
    //Current number of packages
    private int size;
    {
        for(int i = 0; i < 10; i++){
            myPackageList[i] = new myPackage[10];
        }
    }
    private Random random = new Random();

    /**
     * Add a new package into package list
     * @param p new package
     * @return true if success else false
     */
    public boolean add(myPackage p){
        if(size == 100){
            return false;
        }
        //Generate 2 random index
        int x = -1;
        int y = -1;
        while(true){
            x = random.nextInt(10);
            y = random.nextInt(10);
            if(myPackageList[x][y] == null){
                //No package in this place
                break;
            }
        }
        //Generate package code
        int code = randomCode();
        p.setCode(code);
        myPackageList[x][y] = p;
        return true;
    }

    /**
     * Generate random package code
     * @return generated code
     */
    private int randomCode(){
        //Generate six digits code
        while(true){
            int code = random.nextInt(900000)+100000;
            myPackage p = findByCode(code);
            if(p == null) {
                return code;
            }
        }
    }

    /**
     * Searching for package using package number
     * @param number package number
     * @return Searching result, null if not exist
     */
    public myPackage findByNumber(String number){
        myPackage p = new myPackage();
        p.setNumber(number);
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(p.equals(myPackageList[i][j])){
                    return myPackageList[i][j];
                }
            }
        }
        return null;
    }


    /**
     * Searching for package using package code
     * @param code package code
     * @return Searching result, null if not exist
     */
    public myPackage findByCode(int code){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(myPackageList[i][j] != null)
                    if(myPackageList[i][j].getCode() == code){
                        return myPackageList[i][j];
                    }
            }
        }
        return null;
    }

    /**
     * (Not necessary step)
     * @param oldP
     * @param newP
     */
    public void update(myPackage oldP, myPackage newP){
        delete(oldP);
        add(newP);
    }

    /**
     * delete package from package list
     * @param p the package to delete
     */
    public void delete(myPackage p){
        s: for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(p.equals(myPackageList[i][j])){
                    myPackageList[i][i] = null;
                    break s;
                }
            }
        }
    }

    public myPackage[][] findAll(){
        return myPackageList;
    }
}