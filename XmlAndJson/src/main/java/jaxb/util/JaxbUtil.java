package jaxb.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {
	public static<T> boolean marshall(T t, File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.marshal(t, file);
			
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static <T> T unmarshall(T t, File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			return (T)unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
