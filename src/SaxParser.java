import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser extends DefaultHandler  {

    private static final String XSD_PATH = "employee.xsd";
    private String path;
    private String tmpValue;
    private Employee tmpEmployee;
    private Passport tmpPassport;
    private List<Employee> employees;

    public SaxParser(String path) throws FileNotFoundException {

        if (XsdValidation.validateAgainstXSD(new FileInputStream(path), new FileInputStream(XSD_PATH))){
            this.path = path;
            employees = new ArrayList<>();
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("employee")){
           tmpEmployee = new Employee();
        }
        if (qName.equalsIgnoreCase("passport")){
            tmpPassport = new Passport();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("employee")){
            employees.add(tmpEmployee);
        }
        if (qName.equalsIgnoreCase("passport")){
            tmpEmployee.setPassport(tmpPassport);
        }
        if(qName.equalsIgnoreCase("seria")){
            tmpPassport.setSeria(tmpValue);
        }
        if (qName.equalsIgnoreCase("number")){
            tmpPassport.setNumber(Integer.parseInt(tmpValue));
        }
        if (qName.equalsIgnoreCase("firstName")){
            tmpEmployee.setFirstName(tmpValue);
        }
        if (qName.equalsIgnoreCase("lastName")){
            tmpEmployee.setLastName(tmpValue);
        }
        if (qName.equalsIgnoreCase("age")){
            tmpEmployee.setAge(Integer.parseInt(tmpValue));
        }
        if (qName.equalsIgnoreCase("experience")){
            tmpEmployee.setExperience(Integer.parseInt(tmpValue));
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tmpValue = new String(ch, start, length);
    }

    public List<Employee> read(){
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try{
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(path);
            parser.parse(file,this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
