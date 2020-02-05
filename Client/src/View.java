
public interface View {
	public void init(Control c);
	public Student obtainNewStudent();
	public int getStudentID(int datalocation_index, boolean delete);
}
