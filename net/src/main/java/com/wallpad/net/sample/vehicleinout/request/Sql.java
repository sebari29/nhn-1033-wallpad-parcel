package com.wallpad.net.sample.vehicleinout.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "ControlRequest")
public class Sql {
    @JacksonXmlProperty(localName = "id")
    private String id;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "param")
    private List<Param> param;

    @JsonCreator
    public Sql(@JacksonXmlProperty(localName = "id") String id,
               @JacksonXmlProperty(localName = "param") List<Param> param) {
        this.id = id;
        this.param = param;
    }
}
