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
    List<myPackage> locker;

    // Get SqlSession, Mapper
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);

    @DisplayName("Initialization Successful")
    void initAll(int number){
        // Allocating packages
        System.out.println("$$$ Allocating "+number+" packages locally...");
        String[] companies = {"DHL", "UPS", "FedEx", "USPS"};
        locker = new ArrayList<>();

        // Initializing packages information
        System.out.println("$$$ Initializing packages information...");
        for (int i = 0; i < number; i++) {
            myPackage p = new myPackage();
            p.setNumber(p.generateNumber());
            p.setCompany(companies[i % 4]);
            p.setCode(p.hashCode());
            p.setCurrentTime(currentTime);
            locker.add(p);
        }
    }

    @DisplayName("LoadUp() Successful")
    void testLoadUp(){
        locker = new ArrayList<>();
        locker.addAll(mapper.checkAll());
        locker.forEach(System.out::println);
        System.out.println("$$$ CheckAll after LoadUp...");
    }

    @DisplayName("Insert() Successful")
    void testInsert(){
        System.out.println("$$$ Inserting packages into the database...");
        boolean repeat;
        int code;
        myPackage[] packages = new myPackage[locker.size()];
        locker.toArray(packages);
        for (myPackage p : packages) {
            repeat = true;
            code = p.getCode();
            // while loop to check if code exists
            while (repeat) {
                // Check if the code is used
                if (mapper.findByCode(code) == null) {
                    mapper.insert(p);
                    repeat = false;
                } else {
                    // Regenerate a new code for the package
                    int rand = (int) Math.abs(Math.random() + code);
                    code = Math.abs((p.hashCode() << 3) + rand) % 999999;
                    while (code < 100000) {
                        code <<= 1;
                    }
                    p.setCode(code);
                }
            }
        }
    }

    @DisplayName("Update() Successful")
    void testUpdate(){
        System.out.println("$$$ Updating new package in the database...");
        String[] companies = {"USPS", "FedEx", "UPS", "DHL"};
        for (int i = 0; i < locker.size(); i++) {
            mapper.update(
                    locker.get(i).getNumber(),
                    locker.get(i).getNumber(),
                    companies[i % 4],
                    currentTime);
        }
    }

    @DisplayName("Delete() Successful")
    void testDelete(){
        System.out.println("$$$ Deleting all packages from the locker in the database...");
        String num;
        myPackage[] packages = new myPackage[locker.size()];
        locker.toArray(packages);
        for (myPackage p : packages) {
            num = p.getNumber();
            p = mapper.findByNumber(num);
            if (p != null) {
                mapper.deleteByNumber(num);
                locker.remove(p);
            } else {
                System.out.println("### Trying to delete NULL... ###");
            }
        }
    }

    @DisplayName("FindByNumber() Successful")
    void testFindByNumber(){
        System.out.println("$$$ Finding all packages inside the locker in the database...");
        String num;
        myPackage p;
        for (int i = 0; i < locker.size(); i+=2) {
            num = locker.get(i).getNumber();
            p = mapper.findByNumber(num);
            if (p != null) {
                System.out.println("Package Found: "+p);
            } else {
                System.out.println("A null package found, returning...");
                return;
            }
        }
    }

    @DisplayName("getCount() Successful")
    void testGetCount(){
        System.out.println(
                "$$$ Getting the amount of packages inside the locker..." +
                "There are { " + mapper.getCount() + " } packages inside the locker right now!");
    }

    @Test
    @DisplayName("full_test Successful")
    public void full_test(){
//        // Test for all functions, change num to start with
        int num = 100;
        System.out.println("$$$ Starting Test with "+num+" packages $$$");
        initAll(num);
        testInsert();
        testUpdate();
        testFindByNumber();
        testGetCount();
        testDelete();
        System.out.println("$$$ Ending Test with "+num+" packages $$$");

//         Test for Loading packages into the locker, deleting packages as well
//         Same operation as truncating the database.
//        System.out.println("$$$ testLoadUp $$$");
//        testLoadUp();
//        testGetCount();
//        testDelete();

        // Close SqlSession
        sqlSession.close();
    }

}
