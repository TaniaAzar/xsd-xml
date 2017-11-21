import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EmployeeApp {
    public static void main(String[] args) throws IOException, ParserConfigurationException,SAXException {

        System.out.println(XsdValidation.validateAgainstXSD(new FileInputStream("employee.xml"), new FileInputStream("employee.xsd")));
        SaxParser paser = new SaxParser("employee.xml");


        List<Employee> employees = paser.read();

        for (Employee item : employees){
            System.out.println(item);
        }
    }
}
