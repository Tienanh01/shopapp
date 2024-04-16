package com.project.shopapp.Test;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringReader;
import java.util.Date;

@XmlRootElement(name = "book")
public class Book {

    private int id;
    private String title;
    private Date date;

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void main(String[] args) {
        try {
            String xmlData = "<book id=\"1\">\n" +
                    "    <title name=\"book\">Book1</title>\n" +
                    "    <date name=\"date\">2016-11-10 23:44:18</date>\n" +
                    "</book>";

            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Book book = (Book) unmarshaller.unmarshal(new StringReader(xmlData));

            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Date: " + book.getDate());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

