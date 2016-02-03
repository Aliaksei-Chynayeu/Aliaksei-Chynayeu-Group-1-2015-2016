package com.epam.minsk.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;


public class MarshalUtil {
	
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(MarshalUtil.class);
	
	/**
     * Unmarshal XML to Wrapper and return List value.
     */
    public static <T> List<T> unmarshal(Unmarshaller unmarshaller,
            Class<T> clazz, String xmlLocation) throws JAXBException {
        StreamSource xml = new StreamSource(xmlLocation);
        WrapperXML<T> wrapper = (WrapperXML<T>) unmarshaller.unmarshal(xml,
                WrapperXML.class).getValue();
        return wrapper.getItems();
    }
    
    /**
     * Marshal List value to XML.
     */
    public static <T> void marshal(Marshaller marshaller,
    	List<T> list, String xmlLocation, Class clazz) throws JAXBException {
    	try {
        JAXBElement element = createCollectionElement(getNameForWrapper(clazz), list);
        marshaller.marshal(element, new FileOutputStream(xmlLocation));
		} catch (FileNotFoundException e) {
			LOG.error("JAXBException" + e);
		} 
    }
    
    /**
     * Creates Object from list for marshalling .
     */
    private static <T> JAXBElement createCollectionElement(String rootName, List<T> list)
    {
    	WrapperXML wrappedObject = new WrapperXML(list);
        return new JAXBElement<WrapperXML>(new QName(rootName), WrapperXML.class, wrappedObject);
    }
    
    /**
     * Creates name of tag for XML
     */
    private static	String getNameForWrapper(Class clazz) {
    		return clazz.getSimpleName().toLowerCase() + "s";
    }
    

}
