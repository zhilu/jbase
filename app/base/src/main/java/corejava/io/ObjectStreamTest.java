package corejava.io;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 将对象通过序列化写出到文件
 * @version 1.10 17 Aug 1998
 * @author Cay Horstmann
 */
class ObjectStreamTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		EmployeeForObjectStream harry = new EmployeeForObjectStream("Harry Hacker", 50000, 1989, 10, 1);
		ManagerForObjectStream carl = new ManagerForObjectStream("Carl Cracker", 80000, 1987, 12, 15);
		carl.setSecretary(harry);
		ManagerForObjectStream tony = new ManagerForObjectStream("Tony Tester", 40000, 1990, 3, 15);
		tony.setSecretary(harry);

		EmployeeForObjectStream[] staff = new EmployeeForObjectStream[3];

		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;

		// save all employee records to the file employee.dat
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/corejava/io/employee.dat"))) {
			out.writeObject(staff);
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/corejava/io/employee.dat"))) {
			// retrieve all records into a new array

			EmployeeForObjectStream[] newStaff = (EmployeeForObjectStream[]) in.readObject();

			// raise secretary's salary
			newStaff[1].raiseSalary(10);

			// print the newly read employee records
			for (EmployeeForObjectStream e : newStaff)
				System.out.println(e);
		}
	}
}

class EmployeeForObjectStream implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6083720215807317951L;
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeForObjectStream() {
	}

	public EmployeeForObjectStream(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}

class ManagerForObjectStream extends EmployeeForObjectStream {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6106742149531608891L;
	private EmployeeForObjectStream secretary;

	/**
	 * Constructs a Manager without a secretary
	 * 
	 * @param n
	 *            the employee's name
	 * @param s
	 *            the salary
	 * @param year
	 *            the hire year
	 * @param month
	 *            the hire month
	 * @param day
	 *            the hire day
	 */
	public ManagerForObjectStream(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		secretary = null;
	}

	/**
	 * Assigns a secretary to the manager.
	 * 
	 * @param s
	 *            the secretary
	 */
	public void setSecretary(EmployeeForObjectStream s) {
		secretary = s;
	}

	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}
}
