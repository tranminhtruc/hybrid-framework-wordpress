package javaBasic;

public class Topic_01_System_Property {
	public static void main (String[] args) {
		//Get current path
		String projectLocation=System.getProperty("user.dir");
		// Get current window
		String osName=System.getProperty("os.name");
	}

}
