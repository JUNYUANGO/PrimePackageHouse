package org.pph.mapper;

import org.apache.ibatis.annotations.Param;
import org.pph.pojo.myPackage;
import java.util.List;

public interface PackageMapper {
    /**
     * Insert a new package into locker
     * @param p: myPackage
     * @return true if success else false
     */
    boolean insert(myPackage p);

    /**
     * Update a package inside the locker
     * @param oldNumber: old package number
     * @param newNumber: new package number
     * @param newCompany: new package company
     * @param currentTime: operated time
     * @return true if success else false
     */
    boolean update(@Param("oldNum") String oldNumber,
                   @Param("newNum") String newNumber,
                   @Param("newCompany") String newCompany,
                   @Param("updateTime") String currentTime);

    /**
     * Delete a package inside the locker by its number
     * @param number: package number
     * @return true if success else false
     */
    boolean deleteByNumber(@Param("number") String number);

    /**
     * Find a package inside the locker by its number
     * @param number: package number
     * @return the queried package
     */
    myPackage findByNumber(@Param("number") String number);

    /**
     * Find a package inside the locker by its code
     * @param code: package code
     * @return the queried package
     */
    myPackage findByCode(@Param("code") int code);

    /**
     * Show all packages inside the locker
     * @return all packages
     */
    List<myPackage> checkAll();
}
