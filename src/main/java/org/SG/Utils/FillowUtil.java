package org.SG.Utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FillowUtil {

static String FILEPATH = System.getProperty("user.dir") + "/src/main/java/org/SG/resources/TDF.xlsx";

public static String fetchDatafromXLSX(String sheetname,String id, String fieldname) throws  FilloException {
    String value = null;
    Fillo fillo= new Fillo();
    Connection connection = fillo.getConnection(FILEPATH);
 String  Query = "Select * from " + sheetname + "" + "where ID=" +id + "";
    Recordset  recordset = connection.executeQuery(Query);
    while(recordset.next())
    {
        value = recordset.getField(fieldname);
    }
  recordset.close();
    connection.close();
    return value;

}

}
