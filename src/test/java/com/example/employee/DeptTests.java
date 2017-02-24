package com.example.employee;

import java.sql.Connection;
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
public class DeptTests {
	
	@Autowired
	DataSource dataSource;
	
	DataSource dataSource1;
	
	@Test
	public void test01_select() throws SQLException {
		System.out.println("##########");
		System.out.println("##select##");
		System.out.println("##########");
	
		System.out.println("dataSource = " + dataSource);
		System.out.println("dataSource1 = " + dataSource1);
		
		/*
		 * 1. Connection
		 */
		Connection con = dataSource.getConnection();
		
		/*
		 * 2. execute Query
		 */
		PreparedStatement pstmt = con.prepareStatement("select * from dept");
		ResultSet rs = pstmt.executeQuery();			// rs 안에 dept 테이블 결과가 있음.
		
		/*
		 * 3. ResultSet print
		 */
		while(rs.next()) {
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");
			System.out.println(deptno + ", " + dname + ", " + loc);
		}
	}
	
	@Test
	public void test02_insert() throws SQLException {
		System.out.println("##########");
		System.out.println("##insert##");
		System.out.println("##########");
		
		/*
		 * 1. Connection
		 */
		Connection con = dataSource.getConnection();
		
		/*
		 * 2. execute Update
		 */
		PreparedStatement pstmt = con.prepareStatement("insert into dept values (?, ?, ?)");  //placehold
		pstmt.setInt(1, 50);
		pstmt.setString(2, "총무부");
		pstmt.setString(3, "서울");
		
		/*
		 * 3. result 확인
		 */
		int rtn = pstmt.executeUpdate();
		System.out.println("rtn = " + rtn);
		
	}
	
	@Test
	public void test03_update() throws SQLException {
		System.out.println("##########");
		System.out.println("##update##");
		System.out.println("##########");
		
		/*
		 * 1.Connection
		 */
		Connection con = dataSource.getConnection();
		
		/*
		 * 2. execute Update
		 */
		PreparedStatement pstmt = con.prepareStatement("update dept set dname =?, loc=? where deptno=?");
		pstmt.setString(1, "xxx");
		pstmt.setString(2, "yyy");
		pstmt.setInt(3, 50);
		
		/*
		 * 3. 결과확인
		 */
		int rtn = pstmt.executeUpdate();
		System.out.println("update rtn = " + rtn);
	}
	
	@Test
	public void test04_delete() throws SQLException {
		System.out.println("##########");
		System.out.println("##delete##");
		System.out.println("##########");
		
		/*
		 * 1. Connection
		 */
		Connection con = dataSource.getConnection();
		
		/*
		 * 2. execute Update
		 */
		PreparedStatement pstmt = con.prepareStatement("delete from dept where deptno=?");
		pstmt.setInt(1, 50);
		
		/*
		 * 3. 결과확인
		 */
		int rtn = pstmt.executeUpdate();
		System.out.println("rtn = " + rtn);
	}
}
