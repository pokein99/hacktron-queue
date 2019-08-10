package com.dbs.hacktron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
/**
 * Entity class. Used for mapping Employee object with its corresponding table. 
 * 
 */


@Entity
@Table(name = "EMPLOYEE_REGISTRATION")
public class EmployeeEntity  implements java.io.Serializable {
	
	/**
	 * Default serialization id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "EMPLOYEE_NAME", nullable = false)
	private String employeeName;
	
	@Column(name = "EMPLOYEE_NUMBER", nullable = false,unique=true)
	private String employeeNumber;
	
	@Transient
	@Column(name = "PASSWORD", nullable = false)	
	private String password;
	
	@Column(name = "DESIGNATION", nullable = false)
	private String designation;
	
	@Column(name = "SERVICE_LINE", nullable = false)
	private String serviceLine;
	
	@Column(name = "ROLE", nullable = false)
	private String role;

	/**
	 * @return id: getting id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id: setting id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return employeeName: getting employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName: setting employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return employeeNumber: getting employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber: setting employeeNumber
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return password: getting password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password : setting password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return designation: getting designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation: setting designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return serviceLine: getting serviceLine
	 */
	public String getServiceLine() {
		return serviceLine;
	}

	/**
	 * @param serviceLine : setting serviceLine
	 */
	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}

	/**
	 * @return role : getting role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role  : setting role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	

}
