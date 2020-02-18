package com.wallpad.net.sample.basicinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Input")
public class Data {
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    public String name;

    @JacksonXmlText
    public String valueData;

    @JsonCreator
    public Data(@JacksonXmlProperty(isAttribute = true, localName = "name") String name,
                @JacksonXmlProperty(localName = "Data") String valueData) {
        this.name = name;
        this.valueData = valueData;
    }
}