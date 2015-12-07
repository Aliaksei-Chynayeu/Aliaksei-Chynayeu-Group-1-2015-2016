package com.epam.minsk.xml;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class MarshalUtil {
	
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

}
