import java.util.ArrayList;

public class Control {
	
	private Data[] data = {new Txt("test.txt"),new Mysql("server.ini"), new Hibernate(), new Web("localhost",8080)};
	private View v;
	
	Control() {
		/*System.out.println("1. Interfaz de texto");
		System.out.println("2. interfaz gráfica");
		switch (in.nextLine()) {
		case "1":
			v=new TerminalView();
			break;
		case "2":
			v=new GUIView();
			break;
		default:
			break;
		}*/
		v = new TerminalView();
	}
	
	public void init() {
		v.init(this);
	}
	
	public boolean addStudentIn(int index) {
		return data[index].addStudent(v.obtainNewStudent());
	}
	
	public ArrayList<String> listStudents(int index) {
		ArrayList<String> rval = new ArrayList<String>();
		ArrayList<Student> data_arls = data[index].getStudentList();
		try {
			for (int i = 0; i < data_arls.size(); i++) {
				Student s = data_arls.get(i);
				rval.add(s.getID() + ". " + s.getName() + " " + s.getApellido() + " (" + s.getEdad() + ")");
			}
		} catch (Exception e) {}
		return rval;
	}
	
	public boolean deleteStudentFrom(int index) {
		return data[index].deleteStudent(v.getStudentID(index, true));
	}
	
	public ArrayList<String> getDataTypes() {
		ArrayList<String> rval = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			rval.add(data[i].getDataType());
		}
		return rval;
	}
	
	public int getDataLength() {
		return data.length;
	}

	public boolean copyall(int origin, int target) {
		boolean success = true;
		try {
			ArrayList<Student> studentlist = data[origin].getStudentList();
			for (Student s : studentlist) {
				boolean succ_one = data[target].addStudent(s);
				success = success && succ_one;
			}
		} catch (Exception e) {
			return false;
		}
		return success;
	}

	public boolean empty(int index) {
		return data[index].clear();
	}

	public void clearAll() {
		for (Data d : data) {
			d.clear();
		}
		
	}

	public boolean editStudent(int index) {
		if (data[index].deleteStudent(v.getStudentID(index,false))) {
			return data[index].addStudent(v.obtainNewStudent());
		}
		return false;
	}

}
