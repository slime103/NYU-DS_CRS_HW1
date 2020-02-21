import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class CourseData implements Serializable{

	private static final long serialVersionUID = 1L;
	//public static ArrayList<Admin> admins = new ArrayList<Admin>();
	public static ArrayList<Student> students = new ArrayList<Student>();
	public static ArrayList<Course> courses = new ArrayList<Course>();
	
	public CourseData() {
		
	}
	
	public static void importCSV() {
		try {
			Scanner scan = new Scanner(new File("MyUniversityCourses.csv"));
			
			//advances past column titles
			scan.nextLine();
			
			while (scan.hasNextLine()) {
				String temp = scan.nextLine();
				String[] lineText = temp.split(",");
				
				//skips indices 3 & 4 because they are not used (current number of students and list of students)
				courses.add(new Course(lineText[0],lineText[1],Integer.parseInt(lineText[2]),lineText[5],
						Integer.parseInt(lineText[6]),lineText[7]));
			}
			
			scan.close();
			
			System.out.println("Course Data imported from MyUniversityCourses.csv");
		} catch (FileNotFoundException e) { 
			System.out.println("Course Data NOT found!");
		}
	}
	
	public static void serialize(CourseData cd) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("CourseData.ser");
			ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objOutputStream.writeObject(cd);
			
			fileOutputStream.close();
			objOutputStream.close();
			
			//Debug
			System.out.println("CourseData Serializated");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deserialize(CourseData cd) {
		try {
			FileInputStream fileInput = new FileInputStream("Employee.ser");
		    ObjectInputStream objInput = new ObjectInputStream(fileInput);
		    
		    cd = (CourseData) objInput.readObject();
		    fileInput.close();
		    objInput.close();
		    
		    //Debug
		    System.out.println("CourseData Deserialized");
		} catch (FileNotFoundException e) { 
			importCSV();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
