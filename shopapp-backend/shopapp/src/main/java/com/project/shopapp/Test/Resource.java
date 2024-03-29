package com.project.shopapp.Test;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement(name = "resource")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class Resource {

    @XmlAttribute(name = "method")
    private String method;

    @XmlAttribute(name = "path")
    private String path;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "call-query")
    private CallQuery callQuery;

    // Các phương thức getter và setter

    // Constructors
}