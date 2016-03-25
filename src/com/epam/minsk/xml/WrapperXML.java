package com.epam.minsk.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

public class WrapperXML<T> {
	 
    private List<T> items;
 
    public WrapperXML() {
        items = new ArrayList<T>();
    }
 
    public WrapperXML(List<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }
}
