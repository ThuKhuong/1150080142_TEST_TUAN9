package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.UserData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    public static List<UserData> readUsers(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<UserData>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot read JSON file: " + filePath, e);
        }
    }
}