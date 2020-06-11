package Repository.Utilitati;

import java.util.Properties;

public class ApplicationContext {
    private static final Properties PROPERTIES= Configurare.getProperties();

    public static Properties getPROPERTIES() {
        return PROPERTIES;
    }
}