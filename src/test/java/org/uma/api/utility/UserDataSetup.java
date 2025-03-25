package org.uma.api.utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.uma.api.utils.ExcelReader;

public class UserDataSetup {

	/* data setup via Excel reader */
	private final String path = System.getProperty("user.dir") + "/src/test/resources/ExcelData/Usersdata.xlsx";

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {

		ExcelReader xl = new ExcelReader(path);
		int rownum = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);

		String[][] apidata = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}

		return apidata;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {

		ExcelReader xl = new ExcelReader(path);
		int rownum = xl.getRowCount("Sheet1");

		String[] apidata = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
		}

		return apidata;
	}

}
