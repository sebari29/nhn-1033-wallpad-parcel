package com.wallpad.net.sample.parcelnotification.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JacksonXmlRootElement(localName = "InputList")
public class Input {
    String dong;

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    public String size;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Data")
    public List<Data> data = new ArrayList<>();

    private HashMap<String,Object> map = new HashMap<>();


    public HashMap<String, Object> getMap(){
        for (Data data :data) {
            map.put(data.name,data.valueData);
        }
        return map;
    }

//    @JsonCreator
//    public Input(@JacksonXmlProperty(isAttribute = true, localName = "size") String size,
//                 @JacksonXmlProperty(localName = "Data") List<Data> data) {
//        this.size = size;
//        this.data = data;
//    }
}
