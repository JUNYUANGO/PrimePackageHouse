package org.pph.pojo;

import java.util.Objects;

public class myPackage {
    // myPackage Number
    private String number;

    // myPackage Company
    private String company;

    // myPackage Code
    private int code;
    private String currentTime;

    // Constructors
    public myPackage() {}
    public myPackage(String number, String company, int code) {
        this.number = number;
        this.company = company;
        this.code = code;
    }

    // Setter & Getter
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // Return a random package number
    public String generateNumber(){
        return Integer.toString((int) Math.abs((Math.random() + 8) * 88888888) % 99999999);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "myPackage{" +
                "number='" + number + '\'' +
                ", company='" + company + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        myPackage aMyPackage = (myPackage) o;
        return Objects.equals(number, aMyPackage.number);
    }

    @Override
    public int hashCode() {
        String tempNum = getNumber();
        String tempCompany = getCompany();
        //hash function
        int code =
                (((26*tempNum.charAt(0) << 3)
                + (26*tempNum.charAt(1) << 2)
                +(26*tempNum.charAt(tempNum.length()-1) << 1))
                * ((13*tempCompany.charAt(0) << 1)
                + (13*tempCompany.charAt(1) << 1)
                + (13*tempCompany.charAt(tempCompany.length()-1) << 1))) % 999999;
        while (code < 100000) {
            code <<= 3;
        }
        return code;
    }
}