package corejava.io;

import java.io.*;
import java.util.*;

/**
 * 
 * 将对象直接写出到文件
 * @version 1.13 2012-05-30
 * @author Cay Horstmann
 */
public class TextFileTest {
	public static void main(String[] args) throws IOException {
		EmployeeForFile[] staff = new EmployeeForFile[3];

		staff[0] = new EmployeeForFile("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new EmployeeForFile("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new EmployeeForFile("Tony Tester", 40000, 1990, 3, 15);

		// save all employee records to the file employee.dat
		try (PrintWriter out = new PrintWriter("./src/corejava/io/employee.dat", "UTF-8")) {
			writeData(staff, out);
		}

		// retrieve all records into a new array
		try (Scanner in = new Scanner(new FileInputStream("./src/corejava/io/employee.dat"), "UTF-8")) {
			EmployeeForFile[] newStaff = readData(in);

			// print the newly read employee records
			for (EmployeeForFile e : newStaff)
				System.out.println(e);
		}
	}

	/**
	 * Writes all employees in an array to a print writer
	 * 
	 * @param employees
	 *            an array of employees
	 * @param out
	 *            a print writer
	 */
	private static void writeData(EmployeeForFile[] employees, PrintWriter out) throws IOException {
		// write number of employees
		out.println(employees.length);

		for (EmployeeForFile e : employees)
			writeEmployee(out, e);
	}

	/**
	 * Reads an array of employees from a scanner
	 * 
	 * @param in
	 *            the scanner
	 * @return the array of employees
	 */
	private static EmployeeForFile[] readData(Scanner in) {
		// retrieve the array size
		int n = in.nextInt();
		in.nextLine(); // consume newline

		EmployeeForFile[] employees = new EmployeeForFile[n];
		for (int i = 0; i < n; i++) {
			employees[i] = readEmployee(in);
		}
		return employees;
	}

	/**
	 * Writes employee data to a print writer
	 * 
	 * @param out
	 *            the print writer
	 */
	public static void writeEmployee(PrintWriter out, EmployeeForFile e) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(e.getHireDay());
		out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) + "|"
				+ (calendar.get(Calendar.MONTH) + 1) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Reads employee data from a buffered reader
	 * 
	 * @param in
	 *            the scanner
	 */
	public static EmployeeForFile readEmployee(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		String name = tokens[0];
		double salary = Double.parseDouble(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		int month = Integer.parseInt(tokens[3]);
		int day = Integer.parseInt(tokens[4]);
		return new EmployeeForFile(name, salary, year, month, day);
	}
}

class EmployeeForFile {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeForFile() {
	}

	public EmployeeForFile(String n, double s, int year, int month, int day) {
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
