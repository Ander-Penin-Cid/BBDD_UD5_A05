import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Txt implements Data {
	File f;
	Path p;
	Txt(String file_path) {
		f = new File(file_path);
		p = Paths.get(file_path);
	}

	@Override
	public ArrayList<Student> getStudentList() {
	    List<String> lines;
		try {
			lines = Files.readAllLines(p, StandardCharsets.UTF_8);
		} catch (IOException e1) {
			return null;
		}
		ArrayList<Student> rval = new ArrayList<Student>();
		for (int i = 0; i < lines.size(); i++) {
			String[] splitted = lines.get(i).split(";");
			try {
				rval.add(new Student(i,splitted[0],splitted[1],Integer.parseInt(splitted[2])));
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		return rval;
	}

	@Override
	public boolean addStudent(Student s) {
		if (s.getName().contains(";") || s.getApellido().contains(";")) {
			return false;
		}
		try {
			String u = s.getName() + ";" + s.getApellido() + ";" + s.getEdad();
			FileWriter fw = new FileWriter(f,true);
			fw.append(u);
			fw.append(System.getProperty("line.separator"));
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public String getDataType() {
		return "Text file " + p + ":";
	}

	@Override
	public boolean deleteStudent(int id) {
		try {
			List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
			lines.remove(id);
		    Files.write(p, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean clear() {
		try {
		    Files.write(p, new ArrayList<String>(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
