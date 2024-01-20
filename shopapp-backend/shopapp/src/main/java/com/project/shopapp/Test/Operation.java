package com.project.shopapp.Test;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@XmlRootElement(name = "operation")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "call-query")
    private List<CallQuery> callQuery;

    // Các phương thức getter và setter

    // Constructors
}