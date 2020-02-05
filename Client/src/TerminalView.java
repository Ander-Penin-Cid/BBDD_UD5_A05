import java.util.ArrayList;
import java.util.Scanner;

public class TerminalView implements View {
	private static Scanner in = new Scanner(System.in);
	private Control c;
	
	private void printStudentList(int datalocation_index) {
		ArrayList<String> studentlist = c.listStudents(datalocation_index);
		for (String s : studentlist) {
			System.out.println(s);
		}
		System.out.println();
	}
	@Override
	public void init(Control c) {
		this.c=c;
		int o=0;
		while(true) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("1. imprimir todos los datos");
			System.out.println("2. imprimir una base");
			System.out.println("3. guardar un dato nuevo");
			System.out.println("4. borrar un dato");
			System.out.println("5. modificar un dato");
			System.out.println("6. transferir datos");
			System.out.println("7. vaciar una base");
			System.out.println("8. borrar todo");
			System.out.println("9. salir");
			try {
				o=in.nextInt();
			} catch (Exception e) {
				o=0;
			}
			System.out.println("----------------------------------------------------------------");
			ArrayList<String> datatypes = c.getDataTypes();
			switch (o) {
			case 1:
				for (int i = 0; i < c.getDataLength(); i++) {
					System.out.println(datatypes.get(i));
					printStudentList(i);
				}
				break;
			case 2:
				System.out.println("Imprimir base:");
				print_all_datatypes(datatypes);
				printStudentList(in.nextInt());
				break;
			case 3:
				System.out.println("Guardar dato en:");
				print_all_datatypes(datatypes);
				if (c.addStudentIn(in.nextInt())) {
					System.out.println("Datos guardados con éxito");
				}
				else {
					System.out.println("Error al guardar los datos");
				}
				break;
			case 4:
				System.out.println("Borrar dato de:");
				print_all_datatypes(datatypes);
				if (c.deleteStudentFrom(in.nextInt())) {
					System.out.println("Dato borrado con éxito");
				}
				else {
					System.out.println("Error al borrar el dato");
				}
				break;
			case 5:
				System.out.println("Modificar dato de:");
				print_all_datatypes(datatypes);
				if (c.editStudent(in.nextInt())) {
					System.out.println("Dato modificado con éxito");
				}
				else {
					System.out.println("Error al modificar el dato");
				}
				break;
			case 6:
				System.out.println("Origen:");
				print_all_datatypes(datatypes);
				int origin = in.nextInt();
				System.out.println("Destino:");
				print_all_datatypes(datatypes);
				int target = in.nextInt();
				if (c.copyall(origin, target)) {
					System.out.println("Datos copiados con éxito");
				}
				else {
					System.out.println("Error al copiar los datos");
				}
				break;
			case 7:
				System.out.println("Base a vaciar:");
				print_all_datatypes(datatypes);
				if (c.empty(in.nextInt())) {
					System.out.println("Base vaciada con éxito");
				}
				else {
					System.out.println("Error al vaciar la base");
				}
				break;
			case 8:
				System.out.println("BORRAR TODAS LAS BASES? [Y/N]");
				if (in.nextLine().equalsIgnoreCase("Y")) {
					c.clearAll();
				}
				break;
			case 9:
				System.exit(0);
				return;
			default:
				System.out.println("opción inválida");
				break;
			}
		}
	}
	
	@Override
	public Student obtainNewStudent() {
		System.out.println("Introducir datos nuevos:");
		in.nextLine();
		System.out.println("Nombre:");
		String name = in.nextLine();
		System.out.println("Apellido:");
		String apellido = in.nextLine();
		System.out.println("Edad:");
		int edad = in.nextInt();
		return new Student(0,name,apellido,edad);
	}

	@Override
	public int getStudentID(int datalocation_index, boolean delete) {
		System.out.println(delete?"Dato a borrar":"Dato a modificar:");
		printStudentList(datalocation_index);
		return in.nextInt();
	}
	
	private void print_all_datatypes(ArrayList<String> datatypes) {
		for (int i = 0; i < datatypes.size(); i++) {
			System.out.println(i + ". " + datatypes.get(i));
		}
	}

}
