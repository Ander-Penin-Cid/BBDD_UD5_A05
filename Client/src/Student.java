
public class Student {
	private int ID;
	private String name;
	private String apellido;
	private int edad;
	Student(int ID,String name, String apellido, int edad) {
		this.ID = ID;
		this.name = name;
		this.apellido = apellido;
		this.edad = edad;
	}
	Student() {
		
	}
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getApellido() {
		return apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
