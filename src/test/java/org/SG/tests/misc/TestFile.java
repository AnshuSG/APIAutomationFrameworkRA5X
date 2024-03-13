package org.SG.tests.misc;

import com.codoid.products.exception.FilloException;
import org.SG.Utils.FillowUtil;
import org.testng.annotations.Test;

public class TestFile {
   @Test
    public void testFillow() throws FilloException {
      String BASE_URL = FillowUtil.fetchDatafromXLSX("Sheet1","baseurl","Value");
       System.out.println(BASE_URL);
    }
}
