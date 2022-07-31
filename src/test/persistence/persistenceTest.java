package persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class persistenceTest {

    @Test
    void LoadTest() throws Exception {
        JsonReader jsonReaderTest = new JsonReader("./data/two048Test.json");
        System.out.println(jsonReaderTest.readBoard().toString());
        Assertions.assertEquals("[{\"serial\":11,\"value\":0},{\"serial\":12,\"value\":128}," +
                "{\"serial\":13,\"value\":0},{\"serial\":14,\"value\":64}," +
                "{\"serial\":21,\"value\":0},{\"serial\":22,\"value\":0}," +
                "{\"serial\":23,\"value\":0},{\"serial\":24,\"value\":0}," +
                "{\"serial\":31,\"value\":0},{\"serial\":32,\"value\":0}," +
                "{\"serial\":33,\"value\":128},{\"serial\":34,\"value\":64}," +
                "{\"serial\":41,\"value\":0},{\"serial\":42,\"value\":128}," +
                "{\"serial\":43,\"value\":256},{\"serial\":44,\"value\":1024}]",
                jsonReaderTest.readBoard().toString());
        Assertions.assertEquals("[{\"score\":0},{\"score\":0},{\"score\":0},{\"score\":0},{\"score\":0}," +
                "{\"score\":0},{\"score\":0},{\"score\":8},{\"score\":10},{\"score\":4},{\"score\":2},{\"score\":0}," +
                "{\"score\":0},{\"score\":0},{\"score\":0},{\"score\":0}]",JsonReader.readScore().toString());
    }
}
