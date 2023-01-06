package org.pph.pojo;

import java.util.Objects;

public class myPackage {
    // myPackage Number
    private String number;

    // myPackage Company
    private String company;

    // myPackage Code
    private int code;

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
        return Objects.hash(number);
    }
}