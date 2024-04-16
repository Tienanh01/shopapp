package com.project.shopapp.Test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class Query {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "useConfig")
    private String useConfig;

    @XmlElement(name = "sql")
    private String sql;

    @XmlElement(name = "result")
    private Result result;

    // Các phương thức getter và setter

    // Constructors

    public static void main(String[] args) {
        DataQuery dataQuery = new DataQuery("$maccvc(type:long)","$hovaten","$ngaysinh","$chucvu","$phongban","$donvi","$sdt","$email","$acc","$loaicanbo",
                "$CCCD","$hocvan","$trinhdongoaingu","$trinhdochinhtri","$ngaybonhiem","$gioitinh"

        );

        DataQuery dataQuery1 = new DataQuery("1L", "John Doe", "1990-01-01", "Manager", "HR", "Company",
                "123456789", "john.doe@example.com", "johndoe", "Employee",
                "123456789012", "Bachelor", "English", "Intermediate",
                "2023-01-01", "Male");

        List<DataQuery> dataQueries = new ArrayList<>();
        dataQueries.add(dataQuery);
        dataQueries.add(dataQuery1);


        DataObject dataObject = new DataObject();
        dataObject.setDataQuery(dataQueries);
        ObjectMapper mapper = new ObjectMapper();
        String json ="";
        try {
            json = mapper.writeValueAsString(dataObject);
            System.out.println("123123");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Result result = new Result("json", json);

        Query query = new Query("congchucvienchuc","1","select * from abc",result);

        System.out.println(query.toString());
    }
}