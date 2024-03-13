package org.SG.tests.DDT;

import org.SG.Utils.UtilExcel;
import org.testng.annotations.Test;

public class LoginwithDDT {


    @Test(dataProvider="getData" ,dataProviderClass = UtilExcel.class)
    public void testLoginData(String UserName,String Password)
    {
        System.out.println(UserName);
        System.out.println(Password);
        //RestAssured code
    }

}
