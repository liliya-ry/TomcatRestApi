package token;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TokenListener implements ServletContextListener {
    private final Timer timer = new Timer();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        long oneDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        TimerTask cleanerTask = new TokenCleaner();
        timer.scheduleAtFixedRate(cleanerTask, 0, oneDay);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}