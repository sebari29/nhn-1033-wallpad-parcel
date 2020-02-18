package com.wallpad.net.sample.basicinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "ControlRequest")
public class HomeServer {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Version")
    public List<Version> listVersion;

    @JsonCreator
    public HomeServer(@JacksonXmlProperty(localName = "Version") List<Version> list){
        this.listVersion =list;
    }
}
