import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.logging.*;

public class Hibernate implements Data {
	Configuration cfg;
	Session ssn;
	
	Hibernate() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
        ssn=sf.openSession();
	}

	@Override
	public ArrayList<Student> getStudentList() {
		Query q= ssn.createQuery("select e from Student e");
		ArrayList<Student> rvalue = new ArrayList<Student>();
        List results = q.list();
        Iterator equiposIterator= results.iterator();
        while (equiposIterator.hasNext()){
            rvalue.add((Student)equiposIterator.next());
        }
		return rvalue;
	}

	@Override
	public boolean addStudent(Student s) {
		try {
			ssn.beginTransaction();
	        ssn.save(s);
	        ssn.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getDataType() {
		return "Hibernate connection \"" + cfg.getProperty("hibernate.connection.url") + "\":";
	}

	@Override
	public boolean deleteStudent(int id) {
		try {
			ssn.beginTransaction();
			Query q = ssn.createQuery("DELETE FROM Student WHERE id=" + id);
			q.executeUpdate();
			ssn.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean clear() {
		try {
			ssn.beginTransaction();
			Query q = ssn.createQuery("DELETE FROM Student");
			q.executeUpdate();
			ssn.getTransaction().commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
