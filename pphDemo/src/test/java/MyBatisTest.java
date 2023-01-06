import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.pph.mapper.PackageMapper;
import org.pph.utils.SqlSessionUtil;

import org.pph.pojo.myPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    // Get java date
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);

    // Test For Insert
    @Test
    public void testInsert() throws IOException {
        // Get SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        // Get mapper, test the method
        PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);

        boolean result = mapper.insert("77777777", "UPS", 777777, currentTime);

        System.out.println("test-Insert Result: "+result);
        sqlSession.close();
    }

    // Test For Update
    @Test
    public void testUpdate(){
        // Get SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        // Get mapper, test the method
        PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);
        boolean result = mapper.update("88888888", "98888888", "DHL", currentTime);

        System.out.println("test-Update Result: "+result);
        sqlSession.close();
    }

    // Test For Delete
    @Test
    public void testDelete(){
        // Get SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        // Get mapper, test the method
        PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);
        boolean result = mapper.deleteByNumber("99999999");

        System.out.println("test-Delete Result: "+result);
        sqlSession.close();
    }

    // Test For findByNumber
    @Test
    public void testFindByNumber(){
        // Get SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        // Get mapper, test the method
        PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);
        myPackage aPackage = mapper.findByNumber("88888888");

        System.out.println("test-findByNumber Result: "+aPackage);
        sqlSession.close();
    }

    // Test For checkAll
    @Test
    public void testCheckAll(){
        // Get SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        // Get mapper, test the method
        PackageMapper mapper = sqlSession.getMapper(PackageMapper.class);
        List<myPackage> packages = mapper.checkAll();
        packages.forEach(System.out::println);
        sqlSession.close();
    }

    // TODO:
    //  1) test all functions, at least 100 insertions and deletions happened
    //  2) Record the time spent on each function
    //  3) Put results into results.txt
    @Test
    public void testAll(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();


    }
}
