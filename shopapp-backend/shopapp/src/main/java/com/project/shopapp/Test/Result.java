package com.project.shopapp.Test;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "result")
public class Result {

    @XmlAttribute(name = "outputType")
    private String outputType;

    @XmlValue
    private String dataObject;

    // Các phương thức getter và setter

    // Constructors


    public static void main(String[] args) {



    }
}