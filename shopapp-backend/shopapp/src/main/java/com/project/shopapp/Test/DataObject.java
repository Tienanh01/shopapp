package com.project.shopapp.Test;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;
import java.util.Objects;

public class DataObject {
    private List<DataQuery> dataQueries;


    public List<DataQuery> getData() {
        return dataQueries;
    }

    public void setDataQuery(List<DataQuery> dataQueries) {
        this.dataQueries = dataQueries;
    }
}
