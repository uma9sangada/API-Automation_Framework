package org.uma.api.practice.nested_payload;

import java.util.*;

public class CompanyDetails {

	private String companyName;
	private String companyHOCity;
	private String comapanyCEO;
	private int companayPin;
	private List<String> supportedSalaryBanks;
	private List<Integer> pincodesOfCityOffice;
	CompanyPFdetails companyPFdetails;
	List<Employee> employee;
	List<Contractors> contractors;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyHOCity() {
		return companyHOCity;
	}

	public void setCompanyHOCity(String companyHOCity) {
		this.companyHOCity = companyHOCity;
	}

	public String getComapanyCEO() {
		return comapanyCEO;
	}

	public void setComapanyCEO(String comapanyCEO) {
		this.comapanyCEO = comapanyCEO;
	}

	public int getCompanayPin() {
		return companayPin;
	}

	public void setCompanayPin(int companayPin) {
		this.companayPin = companayPin;
	}

	public List<String> getSupportedSalaryBanks() {
		return supportedSalaryBanks;
	}

	public void setSupportedSalaryBanks(List<String> supportedSalaryBanks) {
		this.supportedSalaryBanks = supportedSalaryBanks;
	}

	public List<Integer> getPincodesOfCityOffice() {
		return pincodesOfCityOffice;
	}

	public void setPincodesOfCityOffice(List<Integer> pincodesOfCityOffice) {
		this.pincodesOfCityOffice = pincodesOfCityOffice;
	}

	public CompanyPFdetails getCompanyPFdetails() {
		return companyPFdetails;
	}

	public void setCompanyPFdetails(CompanyPFdetails companyPFdetails) {
		this.companyPFdetails = companyPFdetails;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<Contractors> getContractors() {
		return contractors;
	}

	public void setContractors(List<Contractors> contractors) {
		this.contractors = contractors;
	}

}
