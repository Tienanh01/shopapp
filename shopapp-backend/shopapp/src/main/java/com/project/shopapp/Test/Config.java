package com.project.shopapp.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Data
@XmlRootElement(name = "config")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "property")
    private List<Property> properties;

    // Các phương thức getter và setter

    // Constructors

//    public static void main(String[] args) throws JAXBException {
//        Config config = new Config();
//        config.setId("1");
//
//        List<Property> list = new ArrayList<>();
//        list.add(new Property(1,"#@32323",32));
//
//        config.setId("1");
////        config.setProperties(list );
//
//        JAXBContext context = JAXBContext.newInstance(Config.class);
//        Marshaller mar = context.createMarshaller();
//        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        mar.marshal(config, new File("/home/thupt/Documents/config.dbs"));
//
//    }
}
