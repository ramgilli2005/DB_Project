package com.ots.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConfigManager {
	static final Logger log = Logger.getLogger(ConfigManager.class);
	static Properties m_properties = new Properties();

	static ConfigManager s_instance;

	static {
		loadProperties("../Config.properties");
	}

	private ConfigManager() {
	}

	public static ConfigManager getInstance() {
		if (s_instance == null) {
			s_instance = new ConfigManager();
		}
		return s_instance;
	}

	public static void loadProperties(String fileName) {

		InputStream inputStream = getInstance().getClass().getClassLoader()
				.getResourceAsStream(fileName);

		try {
			m_properties.load(inputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String getString(String key) {
		String value = m_properties.getProperty(key);

		if (value != null) {
			value = value.trim();
		}

		return value;
	}

	public static String getString(String key, String defaultValue) {
		String value = getString(key);

		if (value == null) {
			value = defaultValue;
		}

		return value;
	}

	public static int getInt(String key) {
		int value = 0;
		try {
			value = Integer.parseInt(m_properties.getProperty(key).trim());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}

	public static double getDouble(String key) {
		double value = 0.0;
		try {
			value = Double.parseDouble(m_properties.getProperty(key).trim());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}



}
