package data;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import utils.ExcelReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginDataProvider {

    private static final String FILE_PATH = "src/test/resources/testdata/login_data.xlsx";

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(ITestContext context) {
        String group = context.getCurrentXmlTest().getParameter("group");
        ExcelReader excelReader = new ExcelReader(FILE_PATH);

        List<Map<String, String>> allData = new ArrayList<>();

        if ("smoke".equalsIgnoreCase(group)) {
            allData.addAll(excelReader.getDataFromSheet("SmokeCases"));
        } else if ("regression".equalsIgnoreCase(group)) {
            allData.addAll(excelReader.getDataFromSheet("SmokeCases"));
            allData.addAll(excelReader.getDataFromSheet("NegativeCases"));
            allData.addAll(excelReader.getDataFromSheet("BoundaryCases"));
        }

        Object[][] result = new Object[allData.size()][1];
        for (int i = 0; i < allData.size(); i++) {
            result[i][0] = allData.get(i);
        }

        return result;
    }
}