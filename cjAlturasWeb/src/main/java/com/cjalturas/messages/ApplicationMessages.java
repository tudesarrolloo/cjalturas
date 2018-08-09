package com.cjalturas.messages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class ApplicationMessages {

	private static ApplicationMessages applicationMessages;
	
	private static Properties propertiesMessages;
	

	private ApplicationMessages() {
		loadMessagesProperties();
	}

	public static ApplicationMessages getInstance() {
		if (applicationMessages == null) {
			applicationMessages = new ApplicationMessages();
		}
		return applicationMessages;
	}

	private void loadMessagesProperties() {
		propertiesMessages = new Properties();
		InputStream input = null;

		try {

//			input = new FileInputStream("messages.properties");
			
			input = getClass().getClassLoader().getResourceAsStream("messages.properties");

			// load a properties file
			propertiesMessages.load(input);

			// get the property value and print it out
			System.out.println(propertiesMessages.getProperty("welcome"));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
//	public String getMessage(String messageProperty) {
//		return propertiesMessages.getProperty(messageProperty);
//	}
	
	public String getMessage(String messageProperty, Object ... params) {
		return  MessageFormat.format(propertiesMessages.getProperty(messageProperty), params);
	}
	

}
