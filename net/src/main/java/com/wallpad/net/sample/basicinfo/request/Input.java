package com.wallpad.net.sample.basicinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Input")
public class Input {
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    public String name;
    @JacksonXmlProperty(isAttribute = true, localName = "size")
    public String size;
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    public String id;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Data")
    public List<Data> data;

    @JsonCreator
    public Input(@JacksonXmlProperty(isAttribute = true, localName = "name") String name,
                 @JacksonXmlProperty(isAttribute = true, localName = "size") String size,
                 @JacksonXmlProperty(localName = "Data") List<Data> data) {
        this.name = name;
        this.size = size;
        this.id = id;
        this.data = data;
    }
}
