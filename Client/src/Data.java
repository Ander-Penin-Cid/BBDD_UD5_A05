import java.util.ArrayList;

public interface Data {
	public ArrayList<Student> getStudentList();
	public boolean addStudent(Student s);
	public String getDataType();
	public boolean deleteStudent(int id);
	public boolean clear();
}
