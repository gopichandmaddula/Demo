package app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jaxb.util.JaxbUtil;
import tek.jaxb.Address;
import tek.jaxb.Marks;
import tek.jaxb.Student;

public class JAXBDemo {
	public static void main(String[] args) throws IOException {
		/*************XML********************/
		//unmarshallXML();
		marshallXML();
		
		
		/////////////*****************JSON************************///////
		//marshallJSON();
		//unmarshallJSON();
	}
	
	static void unmarshallXML() {
		File file = new File(JAXBDemo.class.getClassLoader().getResource("MyStudent.xml").getFile());
		Student student = new Student();
		student = JaxbUtil.unmarshall(student, file);
		System.out.println(student);
	}
	
	static void marshallXML() {
		File file = new File(JAXBDemo.class.getClassLoader().getResource("MyStudentOut.xml").getFile());
		Student student = createStudent();
		boolean success = JaxbUtil.marshall(student, file);
		System.out.println(success);
	}
	
	static void unmarshallJSON() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Student student = mapper.readValue(new File(JAXBDemo.class.getClassLoader().getResource("MyStudent.json").getFile()),
									Student.class);
		System.out.println(student);
	}
	
	static void marshallJSON() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Student student = createStudent();
		
		File file = new File(JAXBDemo.class.getClassLoader().getResource("MyStudentOut.json").getFile());
		mapper.writeValue(file, student);
	}
	
	private static Student createStudent() {
		Student student = new Student();
		student.setId("1001");
		student.setName("Lionel Messi");
		student.setEmail("messi@barca.com");
		
		Address add1 = new Address();
		add1.setStreet("5545 street");
		add1.setCity("New York");
		add1.setZip("45785");
		add1.setAddressType("HOME");
		
		Marks marks = new Marks();
		marks.setSubject("History");
		marks.setMarks(65.75f);
		marks.setMaxMarks(85.12f);
		
		student.setAddress(Arrays.asList(add1));
		student.setMarks(Arrays.asList(marks));
		
		return student;
	}
}
