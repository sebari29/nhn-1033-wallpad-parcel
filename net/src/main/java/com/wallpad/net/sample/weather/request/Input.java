package com.wallpad.net.sample.weather.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "InputList")
public class Input {
    @JacksonXmlProperty(isAttribute = true, localName = "size")
    public String size;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    public String name;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Data")
    public List<Data> data;

    @JsonCreator
    public Input(@JacksonXmlProperty(isAttribute = true, localName = "size") String size,
                 @JacksonXmlProperty(isAttribute = true, localName = "name") String name,
                 @JacksonXmlProperty(localName = "Data") List<Data> data) {
        this.size = size;
        this.name = name;
        this.data = data;
    }
}
