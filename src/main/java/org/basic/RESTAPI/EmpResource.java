 package org.basic.RESTAPI;


import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/empresource")
public class EmpResource {
	@GET
	@Path("/getemployees")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Employee> getEmployees() throws ClassNotFoundException, SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		List<Employee> ls = employeeDB.getEmployees();
		return ls;
	}
	
	@GET
	@Path("/getemployee/id")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee getEmployee(@QueryParam("id") int id) throws ClassNotFoundException, SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		Employee employee = employeeDB.getEmployee(id);
		return employee;
	}
	
	@POST
	@Path("/addemployee")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee PostEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		employeeDB.addDB(employee);
		return employee;
		
	}
	
	
	@PUT
	@Path("/updateemployee")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee PutEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		if(employeeDB.getEmployee(employee.getId()) == null) {
			employeeDB.addDB(employee);
			return employee;
			
		}
		
		employeeDB.updateDB(employee);
		return employee;
	}
	
	@DELETE
	@Path("removeemployee/{id}")
	public Employee DeleteEmployee(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		Employee employee = employeeDB.deleteDB(id);
		return employee;
	}
	
}
