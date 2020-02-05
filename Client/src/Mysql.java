import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Mysql implements Data {
	private Database_config cfg;

	private String bd_usr;
	private String bd_pwd;
	private String ip;
	
	private String url;
	private String database = "BBDD_ej1";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conexion;
		
	Mysql(String Configfile) {
		cfg = new Database_config(Configfile);
		bd_usr = cfg.getProperty("User");
		bd_pwd = cfg.getProperty("Password");
		ip = cfg.getProperty("serverIP");
		try {
			url = "jdbc:mysql://" + ip + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, bd_usr, bd_pwd);
		} catch (Exception e1) {
			System.err.println("Unable to connect to database");
			return;
		}
	}

	@Override
	public ArrayList<Student> getStudentList() {
		ArrayList<Student> rval = new ArrayList<Student>();
		try {
			PreparedStatement pstmt;
			pstmt = conexion.prepareStatement("SELECT * FROM students");
			ResultSet rset = pstmt.executeQuery();
			//ResultSetMetaData rsmd = rset.getMetaData();
			while (rset.next()) {
				rval.add(new Student(rset.getInt("id"),rset.getString("name"),rset.getString("surname"),rset.getInt("age")));
			}
		} catch (Exception e) {
			return null;
		}
		return rval;
	}

	@Override
	public boolean addStudent(Student s) {
		try {
			PreparedStatement pstmt;
			pstmt = conexion.prepareStatement("INSERT INTO students (name, surname, age) VALUES (\"" + s.getName() + "\",\"" + s.getApellido() + "\",\"" + s.getEdad() + "\")");
			pstmt.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getDataType() {
		return "MySQL database in server " + cfg.getProperty("serverIP") + ":";
	}

	@Override
	public boolean deleteStudent(int id) {
		try {
			PreparedStatement pstmt;
			pstmt = conexion.prepareStatement("DELETE FROM students WHERE id=" + id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean clear() {
		try {
			PreparedStatement pstmt;
			pstmt = conexion.prepareStatement("DELETE FROM students");
			pstmt.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
