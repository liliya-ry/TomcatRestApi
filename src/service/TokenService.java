package service;

import mappers.TokenMapper;
import model.Token;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TokenService {
    private final SqlSessionFactory factory;

    public TokenService(ServletContext sc) {
        InputStream in = sc.getResourceAsStream("/WEB-INF/mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public TokenService() {
        InputStream in;
        try {
            in = Resources.getResourceAsStream("main/webapp/WEB-INF/mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public List<Token> getAllTokens() {
        try (SqlSession sqlSession = factory.openSession()) {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            return tokenMapper.getAllTokens();
        }
    }

    public Token getToken(String token) {
        try (SqlSession sqlSession = factory.openSession()) {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            return tokenMapper.getToken(token);
        }
    }

    public Token getTokenByUser(int userId) {
        try (SqlSession sqlSession = factory.openSession()) {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            return tokenMapper.getTokenByUser(userId);
        }
    }

    public void insertToken(Token token) {
        try (SqlSession sqlSession = factory.openSession()) {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            tokenMapper.insertToken(token);
            sqlSession.commit();
        }
    }

    public void deleteToken(String token) {
        try (SqlSession sqlSession = factory.openSession()) {
            TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
            tokenMapper.deleteToken(token);
            sqlSession.commit();
        }
    }
}