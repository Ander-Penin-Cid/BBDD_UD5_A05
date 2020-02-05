

import java.io.*;
import java.util.Properties;

public class Database_config extends Properties {
	File f;
	public Database_config(String Configfile) {
		try {
			f = new File(Configfile);
			if (f.exists()) {
				load(new FileInputStream(f));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}