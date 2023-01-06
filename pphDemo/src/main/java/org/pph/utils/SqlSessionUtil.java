package org.pph.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.pph.mapper.PackageMapper;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        try {
            // Get stream for resources
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            // Get SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

            // Get SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

            // Get SqlSession, auto-commit
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlSession;
    }

}
