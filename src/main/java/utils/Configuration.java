package utils;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private Properties properties;

	public Configuration() {
		loadProperty();
	}

	public void loadProperty() {
		properties = new Properties();

		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperties(String Key) {
		return properties.getProperty(Key);
	}
}
