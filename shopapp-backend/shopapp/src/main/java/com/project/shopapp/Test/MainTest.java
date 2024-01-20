package com.project.shopapp.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws JAXBException {


        // create properties
        Property property = new Property("password","123abc@A");
        Property property1 = new Property("url","123abc@A");
        List<Property> list = new ArrayList<>();
        list.add(property);
        list.add(property1);

        Config config = new Config("1",list);
        List<Config> listCf = new ArrayList<>();
        listCf.add(config);


        CallQuery callQuery = new CallQuery("conchucvienchuc");
        List<CallQuery> listCallOperation = new ArrayList<>();
        listCallOperation.add(callQuery);


        Resource resource = new Resource("GET","/congchucvienchuc","",callQuery);

        Operation operation = new Operation("congchucvienchuc",listCallOperation );


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
            System.out.println("");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Result result = new Result("json", json);

        Query query = new Query("congchucvienchuc","1","select * from abc",result);

        Data data = new Data("GetData","active","","","http https","",listCf,resource ,operation,query);





        System.out.println(data.toString());

        JAXBContext context = JAXBContext.newInstance(Data.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(data, System.out);
        mar.marshal(data, new File("/home/thupt/Documents/config.dbs"));
    }
}
