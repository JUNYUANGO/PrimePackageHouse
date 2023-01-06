package org.pph.mapper;

import org.pph.pojo.myPackage;
import java.util.List;

public interface PackageMapper {
    /**
     * Insert a new package into locker
     * @param number: package number
     * @param company: package company
     * @param code: package code
     * @param currentTime: operated time
     * @return true if success else false
     */
    boolean insert(String number, String company, int code, String currentTime);

    /**
     * Update a package inside the locker
     * @param oldNumber: old package number
     * @param newNumber: new package number
     * @param newCompany: new package company
     * @param currentTime: operated time
     * @return true if success else false
     */
    boolean update(String oldNumber, String newNumber, String newCompany, String currentTime);

    /**
     * Delete a package inside the locker by its number
     * @param number: package number
     * @return true if success else false
     */
    boolean deleteByNumber(String number);

    /**
     * Find a package inside the locker by its number
     * @param number: package number
     * @return the queried package
     */
    myPackage findByNumber(String number);

    /**
     * Show all packages inside the locker
     * @return all packages
     */
    List<myPackage> checkAll();

}
