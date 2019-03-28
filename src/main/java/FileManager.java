import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileManager {

    /** TODO połączyć z resztą programu */
    public static void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Test test = new Test(1, "number1");

        objectMapper.writeValue(new File("target/test.json"), test);

        Test test2 = objectMapper.readValue(new File("target/test.json"), Test.class);

        System.out.println(test2.getId() + " " + test2.getName());
    }
}
