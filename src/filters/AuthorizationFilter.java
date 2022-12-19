package filters;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Token;
import service.TokenService;
import utility.ResponseHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class AuthorizationFilter implements Filter {
    private ResponseHandler responseHandler;
    private TokenService tokenService;

    @Override
    public void init(FilterConfig fConfig) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        responseHandler = new ResponseHandler(gson);
        tokenService = new TokenService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        if (!authHeader.startsWith("Bearer")) {
            responseHandler.sendError(resp, SC_BAD_REQUEST, "Authorization method not supported");
            return;
        }

        String tokenHash = authHeader.split(" ")[1];

        if (tokenHash == null || tokenHash.isEmpty()) {
            responseHandler.sendError(resp, SC_UNAUTHORIZED, "Bad request: unauthorised user");
            return;
        }

        Token token = tokenService.getToken(tokenHash);

        if (token == null) {
            responseHandler.sendError(resp, SC_UNAUTHORIZED, "Token does not exist");
            return;
        }

        LocalDate expirationDate = token.getExpirationDate().toLocalDate();
        if (expirationDate.isBefore(LocalDate.now())) {
            responseHandler.sendError(resp, SC_UNAUTHORIZED, "Expired token");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
