package guru.qa.lesson_10.parsing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFileJsonParsingTest {

    private ClassLoader cl = ReadFileJsonParsingTest.class.getClassLoader();

    @Test
    void readJson() throws Exception {
        ObjectMapper gson = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("Car.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Car car = gson.readValue(isr, Car.class);

            Assertions.assertEquals("Lexus", car.brand);
            Assertions.assertEquals("IS F", car.model);
            Assertions.assertEquals(2023, car.year);
            Assertions.assertEquals("AKPP", car.kpp);
            Assertions.assertEquals("V6", car.motor);
        }
    }
}
