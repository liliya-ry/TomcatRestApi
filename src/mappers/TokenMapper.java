package mappers;

import model.Token;

import java.util.List;

public interface TokenMapper {
    void insertToken(Token token);;
    Token getToken(String token);
    Token getTokenByUser(int userId);
    List<Token> getAllTokens();
    void deleteToken(String token);
    void updateToken(Token token);
}