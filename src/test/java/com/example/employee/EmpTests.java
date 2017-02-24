package com.example.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpTests  {
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void test01_select() throws SQLException {
		System.out.println("##########");
		System.out.println("##select##");
		System.out.println("##########");
		
		Connection con = dataSource.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("select * from emp");
		ResultSet rs = pstmt.executeQuery();
	
		while(rs.next()) {
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int mgr = rs.getInt("mgr");
			Date hiredate = rs.getDate("hiredate");
			int sal = rs.getInt("sal");
			int comm = rs.getInt("comm");
			int deptno = rs.getInt("deptno");
			System.out.println(empno + ", " + ename+ ", " + job+ ", " + 
								mgr+ ", " + hiredate+ ", " + sal+ ", " + comm+ ", " + deptno);
		}
	}
	
	@Test
	public void test02_insert() throws SQLException {
		System.out.println("##########");
		System.out.println("##insert##");
		System.out.println("##########");
		
		Connection con =  dataSource.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("insert into emp values (?, ?, ?, ?, ?, ?, ?, ?)");
		
		Date date = new Date(100, 9, 10); // 1900 + year, 1 + month
		pstmt.setInt(1, 7777);
		pstmt.setString(2, "CHLOE");
		pstmt.setString(3, "MANAGER");
		pstmt.setInt(4, 7839);
		pstmt.setDate(5, date);
		pstmt.setInt(6, 4800);
		pstmt.setInt(7, 0);
		pstmt.setInt(8, 20);
		
		int rtn = pstmt.executeUpdate();
		System.out.println("rtn = " + rtn);
	}
	
	@Test
	public void test03_update() throws SQLException {
		System.out.println("##########");
		System.out.println("##update##");
		System.out.println("##########");
		
		Connection con = dataSource.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("update emp set job=?, mgr=? where empno=?");
		pstmt.setString(1, "SALEMAN");
		pstmt.setInt(2, 7698);
		pstmt.setInt(3, 7777);
		
		int rtn = pstmt.executeUpdate();
		System.out.println("rtn = " + rtn);
	}
	
	@Test
	public void test04_delete() throws SQLException {
		System.out.println("##########");
		System.out.println("##delete##");
		System.out.println("##########");
		
		Connection con = dataSource.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("delete from emp where empno=?");
		pstmt.setInt(1, 7777);
		
		int rtn = pstmt.executeUpdate();
		System.out.println("rtn = " + rtn);
	}



}
