package com.project.shopapp.Test;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringWriter;

@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "property")
@Data
public class Tesst {
@XmlAttribute
    private String name;
@XmlValue
    private String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setvalue(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }



    public static void main(String[] args) {
        try {
            // Tạo một đối tượng Property
          Tesst tesst = new Tesst();
            tesst.setName("password");
            tesst.setvalue("123abc@A");

            // Sử dụng JAXB để chuyển đối tượng thành XML
            JAXBContext jaxbContext = JAXBContext.newInstance(Tesst.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // In ra XML
            StringWriter writer = new StringWriter();
            marshaller.marshal(tesst, writer);
            System.out.println(writer.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

