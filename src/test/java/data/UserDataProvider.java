package data;

import models.UserData;
import org.testng.annotations.DataProvider;
import utils.JsonReader;

import java.util.List;

public class UserDataProvider {

    private static final String FILE_PATH = "src/test/resources/testdata/users.json";

    @DataProvider(name = "userJsonData")
    public static Object[][] getUserJsonData() {
        List<UserData> users = JsonReader.readUsers(FILE_PATH);

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
}