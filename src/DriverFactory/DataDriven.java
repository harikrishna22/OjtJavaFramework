package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven 
{ 
	WebDriver driver;
	@BeforeTest
	public void setUp()throws Throwable
	{
		String launch = ERP_Functions.launchApp("http://webapp.qedge.com/");
		Reporter.log(launch, true);
		String login = ERP_Functions.verifyLogin("admin","master");
	}
	@Test
	public void suplierCreation() throws Throwable
	{
		//accessing excel util methods
		ExcelFileUtil xl = new ExcelFileUtil();
		//row count
		int rc = xl.rowCount("suplier");
		int cc = xl.colCount("suplier");
		Reporter.log("no ofrows are::"+rc+"    "+"no of columns are::"+cc, true);
		for(int i= 1; i<=rc;i++)
		{
			String sname = xl.getCellData("suplier", i, 0);
			String address = xl.getCellData("suplier", i, 1);
			String city = xl.getCellData("suplier", i, 2);
			String country = xl.getCellData("suplier", i,3);
			String cperson = xl.getCellData("suplier", i,4);
			String pnum = xl.getCellData("suplier", i, 5);
			String email = xl.getCellData("suplier", i, 6);
			String mnum = xl.getCellData("suplier", i, 7);
			String note = xl.getCellData("suplier", i, 8);
             
            //call supplier creation method
			String results = ERP_Functions.verifySuplier(sname,address,city,country,cperson,pnum,email,mnum,note);
			Reporter.log(results,true);
			
		}
	}
     @AfterTest
     public void tearDown()
     {
    	 ERP_Functions.verifyLogout();
     }
}
