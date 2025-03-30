package org.uma.api.practice.nested_payload;

import java.util.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreatedNestedJSONFromPOJO {

	@Test
	public void generatePOJOData() throws JsonProcessingException {

		CompanyDetails companydetails = new CompanyDetails();
		companydetails.setCompanyName("TCS");
		companydetails.setCompanyHOCity("Bengaluru");
		companydetails.setCompanayPin(560100);
		companydetails.setComapanyCEO("Uma Sangada");

//		List<String> SalaryBanks = new ArrayList<>();
//		SalaryBanks.add("Axis");
//		SalaryBanks.add("Indus");
//		SalaryBanks.add("Detch");

		companydetails.setSupportedSalaryBanks(Arrays.asList("Axis", "Union", "Sbi"));
		companydetails.setPincodesOfCityOffice(Arrays.asList(560100, 560190, 560180));
		
		CompanyPFdetails pfdetails= new CompanyPFdetails();
		pfdetails.setNoofEmployees(10);
		pfdetails.setPfCity("banglore");
		pfdetails.setPfName("santhosha");
		pfdetails.setPfYear(2024);
		
		companydetails.setCompanyPFdetails(pfdetails);
		
		
		Employee employee1= new Employee();
		employee1.setAge(25);
		employee1.setFirstName("sang");
		employee1.setGender("Male");
		employee1.setLastName("Brrok");
		employee1.setMarried(false);
		employee1.setSalary(200000);
		
		Employee employee2= new Employee();
		employee2.setAge(25);
		employee2.setFirstName("sang1");
		employee2.setGender("Male");
		employee2.setLastName("Brrok1");
		employee2.setMarried(false);
		employee2.setSalary(200000);
		
		
		companydetails.setEmployee(Arrays.asList(employee1,employee2));
		
		
		

		
		String nestedPayload = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(companydetails);
		System.out.println(nestedPayload);
	}

}
