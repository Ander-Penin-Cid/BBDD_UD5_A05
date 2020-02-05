import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Web implements Data {
	String address;
	int port;

	Web(String address, int port) {
		this.address = address;
		this.port = port;
	}

	@Override
	public ArrayList<Student> getStudentList() {
		try {
			URL url = new URL("http://" + address + ":" + port + "/getall");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			if (con.getResponseCode() != 200) {
				return null;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			ArrayList<Student> rval = new ArrayList<Student>();
			while ((inputLine = in.readLine()) != null) {
				String[] splitted = inputLine.split(";");
				try {
					rval.add(new Student(Integer.parseInt(splitted[0]),splitted[1],splitted[2],Integer.parseInt(splitted[3])));
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			return rval;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean addStudent(Student s) {
		try {
			URL url = new URL("http://" + address + ":" + port + "/add?name=" + s.getName() + "&surname=" + s.getApellido() + "&age=" + s.getEdad());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");


			return con.getResponseCode() == 200;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getDataType() {
		return "Webserver nodeJS on " + address + ":" + port;
	}

	@Override
	public boolean deleteStudent(int id) {
		try {
			URL url = new URL("http://" + address + ":" + port + "/delete?id=" + id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");


			return con.getResponseCode() == 200;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean clear() {
		try {
			URL url = new URL("http://" + address + ":" + port + "/clear");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");


			return con.getResponseCode() == 200;
		} catch (Exception e) {
			return false;
		}
	}

}
