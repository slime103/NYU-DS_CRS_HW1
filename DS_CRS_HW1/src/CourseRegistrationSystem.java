import java.util.Scanner;

public class CourseRegistrationSystem {
	
	public static void main(String[] args) {
		
		
		/* Testing Checklist
		 * 
		 * Importation Working
		 * Serialization Working
		 * Student Course Registration Working
		 * Student Registration Working
		 * Admin reports working
		 * Admin course manipulation working
		 * 
		 * Class Setup
		 * 
		 * Student and Admin extend User
		 * Student implements StudentView
		 * Admin implements CourseManagement
		 * CourseData contain the 'master' arrays -
		 * which contain a list of courses and registered students
		 * courses contain a list of students and
		 * students contain a list of courses
		 * 
		 * How it Works
		 * 
		 * All commands are called via a Student or Admin object
		 * which are both accessed by logging-in
		 * 
		 * Flow of Control
		 * 
		 * Master program loop ->
		 * Log-in Loop ->
		 * Student Loop & Admin Loop -> 
		 * End of student branch
		 * Course management loop & Reports loop
		 */
		
		//IMPORT DATA
		CourseData.deserialize();
		Scanner scan = new Scanner(System.in);
		
		//User user = new User();
		//System.out.println(user.viewCourses());
		
		//Admin admin = new Admin();
		//System.out.println("\n\n\n" + admin.viewCourses());
		//admin.writeFullCoursesToFile();
		
		Student student = new Student("tempVal","tempVal");
		Admin admin = new Admin();
		
		//admin.registerNewStudent("Mike", "Lee");
		
		System.out.println(student.viewCourses());
		System.out.println(admin.viewStudents());
		
		//Registration testing
		//student.registerInCourse("basic algorithms");
		//System.out.println(student.findCourse("basic algorithms").getStudents());
		//student.withdraw("basic algorithms");
		//System.out.println(student.findCourse("basic algorithms").getStudents());

		//System.out.println(admin.viewCourses());
		
		boolean loggedIn = false;
		boolean studentUser = false;
		boolean adminUser = false;
		boolean running = true;
		
		while (running) {
		
		//PROGRAM START
		System.out.println("Hello! Welcome to Albert 1993.\n"
				+ "Please enter the integer corresponding to your correct login credientials:\n");
		
		//LOGIN
		while (!loggedIn) {
			System.out.println("1. Student\n2. Admin\n3. Quit");
			
			int n = 0;
			
			//Waits for integer input in the specified range (1-3)
			//this loop is used throughout the program whenever integer input is needed
			while(!(n >= 1 && n <= 3)) {
				while (!scan.hasNextInt()) {
					//System.out.println("Please enter '1' or '2'.");
					scan.next();
				}	
				n = scan.nextInt();
				//necessary to "\n" token
				scan.nextLine();
			}
			
			if (n == 3) {
				admin.quit();
			}
			
			System.out.println("Please enter your username: ");
			if (n == 1) {
				System.out.println("(Hint: username is firstname.lastname\n"
						+ "password is $lastname$\n");
			}
			
			String username = scan.next();
			System.out.println("Please enter your password: ");
			
			String password = scan.next();
			switch (n) {
				case 1: {
					try {
						student = student.login(username, password);
						System.out.println("Succesfully logged in as " + student.getName()+"\n");
						loggedIn = true;
						studentUser = true;
					} catch (NullPointerException e) {
						System.out.println("Invalid user credentials or Student does not exist.\n");
					}
					break;
				}
				case 2: {
					if (username.contentEquals("Admin") && password.contentEquals("Admin001")) {
						System.out.println("Succesfully logged in as Admin.\n");
						loggedIn = true;
						adminUser = true;
					}
					else {
						System.out.println("Invalid user credentials.\n");
					}
					break;
				}
			}
		}
		
		//Display STUDENT commands
		while (studentUser) {
			System.out.println("1. View All Courses \n2. View Open Courses \n3. Register in a Course"
					+ "\n4. Withdraw from Course \n5. View All Currently Registered Courses \n6. Log Out\n");
			
			int n = 0;
			
			while(!(n <= 6 && n >= 1)) {
				while (!scan.hasNextInt()) {
					//System.out.println("Please enter a vaild integer.");
					scan.next();
				}	
				n = scan.nextInt();
				//Clear new line token
				scan.nextLine();
			}

			
			switch (n) {
				case 1: {
					System.out.println(student.viewCourses());
					break;
				}
				case 2: {
					System.out.println(student.viewOpenCourses());
					break;
				}
				case 3: {
					System.out.println("Please enter the name of the course you would like to Register in: ");
					student.registerInCourse(scan.nextLine());
					break;
				}
				case 4: {
					System.out.println("Please enter the name of the course you would like to withdraw from: ");
					student.withdraw(scan.nextLine());
					break;
				}
				case 5: {
					System.out.println(student.viewEnrolledCourses());
					break;
				}
				case 6: {
					studentUser = false;
					loggedIn = false;
					System.out.println("Succesfully logged out.\n");
					break;
				}
			}
		}

		
		//Display ADMIN commands
		while (adminUser) {
			System.out.println("1. Course Management \n2. Reports\n3. Log Out");
			
			int n = 0;
			
			while(!(n >= 1 && n <= 3)) {
				while (!scan.hasNextInt()) {
					scan.next();
				}	
				n = scan.nextInt();
			}
			
			if (n == 3) {
				adminUser = false;
				loggedIn = false;
				System.out.println("Succesfully logged out.\n");
			}
			
			// Display COURSE MANAGEMENT commands
			while (n == 1) {
				System.out.println("\n1. Create a New Course \n2. Delete a Course \n3. Edit a Course \n"
						+ "4. Display Course Info \n5. Register a Student \n6. Go Back \n7. Quit\n");
				
				int v = 0;
				
				while(!(v <= 7 && v >= 1)) {
					while (!scan.hasNextInt()) {
						scan.next();
					}	
					v = scan.nextInt();
					scan.nextLine();
				}
				
				switch (v) {
					case 1: {
						System.out.println("Please enter the Name of the course you would like to create: ");
						String name = scan.nextLine();
						
						System.out.println("Please enter the ID: ");
						String id = scan.next();
						
						System.out.println("Please enter the Maximum Number of Students allowed to enroll: ");
						while (!scan.hasNextInt()) {
							scan.next();
						}	
						int max = scan.nextInt();
						scan.nextLine();
						
						System.out.println("Please enter the name of the Instructor: ");
						String prof = scan.nextLine();
						
						System.out.println("Please enter the Section Number: ");
						while (!scan.hasNextInt()) {
							scan.next();
						}	
						int section = scan.nextInt();
						scan.nextLine();
						
						System.out.println("Please enter the Location's name: ");
						String loc = scan.nextLine();
						
						admin.addCourse(name, id, max, prof, section, loc);
						break;
					}
					case 2: {
						System.out.println("Please enter the name of the course you would like to delete: ");
						admin.deleteCourse(scan.nextLine());
						break;
					}
					case 3: {
						System.out.println("Please enter the name of the course you would like to edit: ");
						String name = scan.nextLine();
						
						System.out.println("Please enter the number of the field you would to edit: \n"
								+ "1. Course Name \n2. Course ID \n3. Maximum Number of Students \n"
								+ "4. Professor \n5. Section Number \n6. Name of Location");
						int a = 0;
						while(!(a <= 6 && a >= 1)) {
							while (!scan.hasNextInt()) {
								scan.next();
							}	
							a = scan.nextInt();
							scan.nextLine();
						}
						System.out.println("Please the value of the specified field: ");
						admin.editCourse(name,a,scan.nextLine());
						break;
					}
					case 4: {
						System.out.println("Please enter the Course's ID: ");
						System.out.println(admin.courseInfo(scan.next()));
						break;
					}
					case 5: {
						System.out.println("Please enter the student's first name: ");
						String first = scan.next();
						System.out.println("Please enter the student's last name: ");
						String last = scan.next();
						admin.registerNewStudent(first, last);
						break;
					}
					case 6: {
						n = 0;
						break;
					}
					case 7: {
						admin.quit();
					}
				}
				
			}
			
			//Display REPORTS commands
			while (n == 2) {
				System.out.println("\n1. View All Courses \n2. View All FULL Courses \n"
						+ "3. Save list of Full Courses to File\n"
						+ "4. View Students in a Course \n5. View a Student's Enrolled Courses \n6. Sort Courses "
						+ "by the Number of Enrolled Students \n7. Go Back \n8. Quit\n");
				
				int v = 0;
				
				while(!(v <= 8 && v >= 1)) {
					while (!scan.hasNextInt()) {
						scan.next();
					}	
					v = scan.nextInt();
					scan.nextLine();
				}
				
				switch (v) {
					case 1: {
						System.out.println(admin.viewCourses());
						break;
					}
					case 2: {
						System.out.println(admin.viewFullCourses());
						break;
					}
					case 3: {
						admin.writeFullCoursesToFile();
						break;
					}
					case 4: {
						System.out.println("Please enter the name of the course you would like to view: ");
						System.out.println(admin.studentsInCourse(scan.nextLine()));
						break;
					}
					case 5: {
						System.out.println("Please enter the name of the student");
						System.out.println(admin.studentEnrolledCourses(scan.nextLine()));
						break;
					}
					case 6: {
						admin.sortCourses();
						break;
					}
					case 7: {
						n = 0;
						break;
					}
					case 8: {
						admin.quit();
					}
				}
			}
		}
		
	}
		scan.close();
		CourseData.serialize();
	}
}
