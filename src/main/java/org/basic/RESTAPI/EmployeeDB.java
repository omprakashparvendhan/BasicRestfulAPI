package org.basic.RESTAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {

	public List<Employee> getEmployees() throws ClassNotFoundException, SQLException{
		String url = "jdbc:mysql://localhost:3306/java?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";
		String name = "root";
		String password = "root";
		String query = "SELECT * from emp";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,name,password);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<Employee> ls = new ArrayList<Employee>();
		while(resultSet.next()) {
			Employee employee = new Employee();
			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("name"));
			employee.setAge(resultSet.getInt("age"));
			ls.add(employee);
		}
		connection.close();
		return ls;

	}

	public void addDB(Employee employee) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/java?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String name = "root";
		String password = "root";
		String query = "INSERT INTO emp(id, name, age) values(?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,name,password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);		
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setInt(3, employee.getAge());
		preparedStatement.execute();
		connection.close();
	}

	public Employee getEmployee(int id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/java?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String name = "root";
		String password = "root";
		String query = "SELECT * from emp";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,name,password);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		Employee employee = new Employee();
		while(resultSet.next()) {
			if(resultSet.getInt("id") == id) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				connection.close();
				return employee;
			}
			
		}
		connection.close();
		return null;
	}

	public void updateDB(Employee employee) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/java?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String name = "root";
		String password = "root";
		String query = "UPDATE emp SET name = ?, age = ? where id = ?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,name,password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1,employee.getName());
		preparedStatement.setInt(2,employee.getAge());
		preparedStatement.setInt(3,employee.getId());
		preparedStatement.execute();
		connection.close();
	}

	public Employee deleteDB(int id) throws ClassNotFoundException, SQLException {
		Employee employee = getEmployee(id);
		
		String url = "jdbc:mysql://localhost:3306/java?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String name = "root";
		String password = "root";
		
		String query = "DELETE FROM emp where id = ?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,name,password);
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,id);
		preparedStatement.execute();
		connection.close();
		return employee;
	}

}
