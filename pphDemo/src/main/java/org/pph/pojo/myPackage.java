package org.pph.pojo;

import java.util.Objects;
import java.util.Random;

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
        String tempItem = getNumber();
        int random = (int) ((Math.random() * (999999 - 100000)) + 100000);
        //hash function
        int code =
                ((26*tempItem.charAt(0) << 3)
                + (26*tempItem.charAt(1) << 2)
                +(26*tempItem.charAt(tempItem.length()-1) << 1)
                + random) % 999999;
        while (code < 100000) {
            code *= 13;
        }
        while (code > 999999) {
            code %= 7;
        }
        return code;
    }
}