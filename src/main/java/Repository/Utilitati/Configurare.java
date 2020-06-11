package Repository.Utilitati;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Configurare {
    public static String CONFIG_LOCATION = Objects.requireNonNull(Configurare.class.getClassLoader()
            .getResource("db.config")).getFile();
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIG_LOCATION));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Cannot load configuration properties");
        }
    }
}
