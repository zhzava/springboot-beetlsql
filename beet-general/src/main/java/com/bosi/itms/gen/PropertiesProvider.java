package com.bosi.itms.gen;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesProvider {

	static Properties props;
	
	private PropertiesProvider(){}
	
	private static void initProperties() {
		try {
			props = loadAllProperties("webapp-generator.properties");
			//String basepackage = props.getProperty("basepackage");
			//props.put("basepackage_dir", basepackage.replace('.', '/'));
		}catch(IOException e) {
			throw new RuntimeException("Load Properties error",e);
		}
	}
	
	public static Properties getProperties() {
		if(props == null)
			initProperties();
		return props;
	}
	
	public static String getProperty(String key, String defaultValue) {
		return getProperties().getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return getProperties().getProperty(key);
	}

	public static Properties loadAllProperties(String resourceName) throws IOException {
		Properties properties = new Properties();
		Enumeration urls = PropertiesProvider.class.getClassLoader().getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			InputStream is = null;
			try {
				URLConnection con = url.openConnection();
				con.setUseCaches(false);
				is = con.getInputStream();
				properties.load(is);
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return properties;
	}
}
