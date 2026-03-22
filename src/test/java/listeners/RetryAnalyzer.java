package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int currentRetry = 0;
    private final int maxRetry = ConfigReader.getInstance().getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (currentRetry < maxRetry) {
            currentRetry++;
            System.out.println("[RETRY] " + result.getName() + " - lần retry: " + currentRetry);
            return true;
        }
        return false;
    }
}