import java.util.*;
import java.io.*;

class Student implements Comparable<Student> {
	String name;
	int day;
	int month;
	int year;

	public Student(String name, int day, int month, int year) {
		super();
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	@Override
	public int compareTo(Student target) {
		if (this.year == target.year) {
			if(this.month == target.month) {
				return this.day - target.day;
			}
			return this.month - target.month;
		}
		return this.year - target.year;
	}
}

public class Main {

	private static int n;
	private static List<Student> students;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(students.get(n-1).name);
		System.out.println(students.get(0).name);
	}

	private static void init() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());

		students = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String[] s = input.readLine().split(" ");
			String name = s[0];
			int day = Integer.parseInt(s[1]);
			int month = Integer.parseInt(s[2]);
			int year = Integer.parseInt(s[3]);
			students.add(new Student(name, day, month, year));
		}
		
		Collections.sort(students);
	}
}