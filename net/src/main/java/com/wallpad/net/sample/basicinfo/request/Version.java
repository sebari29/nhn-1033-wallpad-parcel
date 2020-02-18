package com.wallpad.net.sample.basicinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "HomeServer")
public class Version {
    @JacksonXmlProperty(isAttribute = true, localName = "code")
    public String code;
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    public String type;
    @JacksonXmlText
    public String value;

    @JsonCreator
    public Version(@JacksonXmlProperty(localName = "code") String code,
                   @JacksonXmlProperty(localName = "type") String type,
                   @JacksonXmlProperty(localName = "HomeServer") String value){
        this.code = code;
        this.type = type;
        this.value = value;
    }
}
