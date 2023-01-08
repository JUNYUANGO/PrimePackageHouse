import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.apache.ibatis.jdbc.SQL;
import org.junit.jupiter.api.*;
import org.junit.Test;

import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import org.pph.mapper.PackageMapper;
import org.pph.utils.SqlSessionUtil;
import org.pph.pojo.myPackage;

/**
 * The complete test for all pph_sql functions.
 */
public class PphSqlTest {
    // Get updated time
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);

    // Create new packages
    myPackage[] packages;
    List<myPackage> packageList;

    // Get SqlSession, Mapper
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);

    @DisplayName("Initialization Successful")
    void initAll(int number){
        // Allocating packages
        System.out.println("$$$ Allocating "+number+" packages locally...");
        String[] companies = {"DHL", "UPS", "FedEx", "USPS"};
        packages = new myPackage[number];

        // Initializing packages information
        System.out.println("$$$ Initializing packages information...");
        for (int i = 0; i < number; i++) {
            packages[i] = new myPackage();
            packages[i].setNumber(Integer.toString(80000000+i));
            packages[i].setCompany(companies[i % 4]);
            packages[i].setCode(packages[i].hashCode());
            packages[i].setCurrentTime(currentTime);
        }
    }

    @DisplayName("Insert() Successful")
    void testInsert(){
        System.out.println("$$$ Inserting packages into the database...");
        boolean repeat = true;
        int code;

        for (myPackage p : packages) {
            code = p.getCode();

            // TODO: Solve the code conflict.

            mapper.insert(p);
        }
        System.out.println("$$$ CheckAll after insertions...");
        packageList = mapper.checkAll();
        packageList.forEach(System.out::println);
    }

    @DisplayName("Update() Successful")
    void testUpdate(){
        System.out.println("$$$ Updating new package in the database...");
        int newNum = 80000000;
        String[] companies = {"USPS", "FedEx", "UPS", "DHL"};
        for (int i = 0; i < packages.length; i++) {
            mapper.update(
                    packages[i].getNumber(),
                    packages[i].getNumber(),
                    companies[i % 4],
                    currentTime);
        }
        System.out.println("### checkAll after updates...");
        packageList = mapper.checkAll();
        packageList.forEach(System.out::println);
    }

    @DisplayName("Delete() Successful")
    void testDelete(){
        System.out.println("$$$ Deleting odd packages in the database...");
        for (int i = 1; i < packages.length; i+=2) {
            mapper.deleteByNumber(Integer.toString(80000000+i));
        }
        System.out.println("$$$ CheckAll after deletions...");
        packageList = mapper.checkAll();
        packageList.forEach(System.out::println);
    }

    @DisplayName("FindByNumber() Successful")
    void testFindByNumber(){
        System.out.println("$$$ Finding 'even' packages in the database...");
        for (int i = 0; i < packages.length; i+=2) {
            if (mapper.findByNumber(Integer.toString(80000000+i)) == null) {
                System.out.println("A null package found, returning...");
                return;
            }
        }
    }

    @DisplayName("Cleaning Up Successful")
    void cleanUp(){
        System.out.println("$$$ Cleaning up all packages in the database...");
        String num;
        for (int i = 0; i < packages.length; i++) {
            num = packages[i].getNumber();
            if (mapper.findByNumber(packages[i].getNumber()) != null) {
                mapper.deleteByNumber(num);
            }
        }
    }

    @Test
    @DisplayName("full_test Successful")
    public void full_test(){
        for (int i = 100; i < 1000; i+=100) {
            System.out.println("--- Starting Test with "+i+" packages ---");
            initAll(i);
            testInsert();
            testUpdate();
            testDelete();
            testFindByNumber();
            cleanUp();
            System.out.println("--- Ending Test with "+i+" packages ---");
        }
        sqlSession.close();
    }

}
