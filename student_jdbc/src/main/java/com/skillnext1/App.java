package com.skillnext1;

import java.util.*;

public class App {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("\nEnter an Option:");
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Select by ID");
                System.out.println("5. View All");
				System.out.println("6. Branch Count");
                System.out.println("7. Quit");

                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Sem: ");
                        int sem = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Dept: ");
                        String dept = sc.nextLine();

                        Student st = new Student();
                        st.setName(name);
                        st.setSem(sem);
                        st.setDept(dept);

                        dao.insertStudent(st);
                        System.out.println("Student Inserted Successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Student ID to Update: ");
                        int upId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String upName = sc.nextLine();

                        System.out.print("Enter New Sem: ");
                        int upSem = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Dept: ");
                        String upDept = sc.nextLine();

                        Student upSt = new Student();
                        upSt.setId(upId);
                        upSt.setName(upName);
                        upSt.setSem(upSem);
                        upSt.setDept(upDept);

                        dao.updateStudent(upSt);
                        System.out.println("Student Updated Successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Student ID to Delete: ");
                        int delId = sc.nextInt();
                        dao.deleteStudent(delId);
                        System.out.println("Student Deleted Successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Student ID to Select: ");
                        int selId = sc.nextInt();
                        Student student = dao.getStudentById(selId);
                        System.out.println(student != null ? student : "Student not found.");
                        break;

                    case 5:
                        List<Student> students = dao.getAllStudents();
                        students.forEach(System.out::println);
                        break;

					case 6:
						List<Student> branchList = dao.branchCount();
						for (Student s : branchList) {
							System.out.println(s.getDept() + " : " + s.getCount());
						}
						break;

					case 7:
						System.exit(0);

                    default:
                        System.out.println("Invalid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
