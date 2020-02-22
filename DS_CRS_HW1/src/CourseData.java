import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class CourseData{

	//public static ArrayList<Admin> admins = new ArrayList<Admin>();
	public static ArrayList<Student> students = new ArrayList<Student>();
	public static ArrayList<Course> courses = new ArrayList<Course>();
	
	public CourseData() {
		
	}
	
	//Tested
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
			
			System.out.println("Course Data imported from MyUniversityCourses.csv\n");
		} catch (FileNotFoundException e) { 
			System.out.println("Course Data NOT found!\n");
		}
	}
	
	//Tested
	public static void serialize() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("courses.ser");
			ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objOutputStream.writeObject(courses);
			
			fileOutputStream.close();
			objOutputStream.close();
			
			//Debug
			System.out.println("Courses Serialized\n");
			
			fileOutputStream = new FileOutputStream("students.ser");
			objOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objOutputStream.writeObject(students);
			
			fileOutputStream.close();
			objOutputStream.close();
			
			//Debug
			System.out.println("Students Serialized\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Tested
	@SuppressWarnings("unchecked")
	public static void deserialize() {
		try {
			FileInputStream fileInput = new FileInputStream("courses.ser");
		    ObjectInputStream objInput = new ObjectInputStream(fileInput);
		    
		    courses = (ArrayList<Course>) objInput.readObject();
		    
		    fileInput.close();
		    objInput.close();
		    
		    //Debug
		    System.out.println("Courses Deserialized\n");
		    
		    fileInput = new FileInputStream("students.ser");
		    objInput = new ObjectInputStream(fileInput);
		    
		    students = (ArrayList<Student>) objInput.readObject();
		    
		    fileInput.close();
		    objInput.close();
		    
		    //Debug
		    System.out.println("Students Deserialized\n");
		} catch (FileNotFoundException e) { 
			importCSV();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
