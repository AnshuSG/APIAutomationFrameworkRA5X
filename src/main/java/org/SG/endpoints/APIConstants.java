package org.SG.endpoints;

import org.SG.Utils.FillowUtil;
import org.SG.Utils.PropertyReaderUtil;
public class APIConstants {

    //IN FUTURE EXCEL FILE OR FILO
   // public static String BASE_URL = "https://restful-booker.herokuapp.com";

    public static String BASE_URL;

    public static String CREATE_UPDATE_BOOKING_URL ;


    static {
        try {

           // BASE_URL = PropertyReaderUtil.readKey("url");
            BASE_URL = FillowUtil.fetchDatafromXLSX("Sheet1","baseurl","Value");
            CREATE_UPDATE_BOOKING_URL =   "/booking";
        }
        catch(Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }




}
