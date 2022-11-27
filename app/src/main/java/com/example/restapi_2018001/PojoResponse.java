package com.example.restapi_2018001;

import java.util.List;

public class PojoResponse {
    private int dataAllPage;
    private List<DataItem> data;
    private String message;

    public int getDataAllPage(){
        return dataAllPage;
    }

    public List<DataItem> getData(){
        return data;
    }

    public String getStatus(){
        return message;
    }
}
