package com.project.shopapp.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@XmlRootElement(name = "property")
//@XmlType(propOrder = {"id" , "age" ,"name"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {
    @XmlAttribute(name = "name")
    private String name;
    @XmlValue
    private String value ;


    public static void main(String[] args) throws JAXBException {
        Property property = new Property("anh","123abc@A");

        JAXBContext context = JAXBContext.newInstance(Property.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(property, System.out);
        mar.marshal(property, new File("/home/thupt/Documents/config.dbs"));
    }



}
