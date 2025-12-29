package com.skillnext1;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sn1";
    private static final String USER = "root";
    private static final String PASSWORD = "suhas";

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertStudent(Student stu) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO student (name, sem, dept) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, stu.getName());
        stmt.setInt(2, stu.getSem());
        stmt.setString(3, stu.getDept());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Student> getAllStudents() throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDept(rs.getString("dept"));
            list.add(s);
        }
        conn.close();
        return list;
    }

    public Student getStudentById(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM student WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Student s = null;
        if (rs.next()) {
            s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDept(rs.getString("dept"));
        }
        conn.close();
        return s;
    }

    public void deleteStudent(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }

    public void updateStudent(Student stu) throws Exception {
        Connection conn = getConnection();
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, stu.getName());
        stmt.setInt(2, stu.getSem());
        stmt.setString(3, stu.getDept());
        stmt.setInt(4, stu.getId());
        stmt.executeUpdate();
        conn.close();
    }
	public List<Student> branchCount() throws Exception {
		Connection conn = getConnection();
		String sql = "SELECT dept, COUNT(*) AS total FROM student GROUP BY dept";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			Student s = new Student();
			s.setDept(rs.getString("dept"));
			s.setCount(rs.getInt("total"));
			list.add(s);
		}
		conn.close();
		return list;
	}
}
