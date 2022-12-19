package token;

import model.Token;
import service.TokenService;

import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

public class TokenCleaner extends TimerTask {
    private final TokenService tokenService = new TokenService();

    @Override
    public void run() {
        List<Token> tokens = tokenService.getAllTokens();
        LocalDate today = LocalDate.now();
        for (Token token : tokens) {
            if (token.getExpirationDate().toLocalDate().isBefore(today)) {
                tokenService.deleteToken(token.getToken());
            }
        }
    }
}