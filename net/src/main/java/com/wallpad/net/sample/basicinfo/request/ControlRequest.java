package com.wallpad.net.sample.basicinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class ControlRequest {

    @JacksonXmlProperty(localName = "FunctionID")
    public String functionID;
    @JacksonXmlProperty(localName = "FunctionCategory")
    public String functionCategory;
    @JacksonXmlProperty(isAttribute = true, localName = "TransID")
    public String transID;
    @JacksonXmlProperty(localName = "InputList")
    public InputList list;

    @JacksonXmlProperty(localName = "HomeServer")
    public HomeServer homeServer;

    @JsonCreator
    public ControlRequest(@JacksonXmlProperty(localName = "FunctionID") String functionID,
                          @JacksonXmlProperty(localName = "FunctionCategory") String functionCategory,
                          @JacksonXmlProperty(isAttribute = true, localName = "TransID") String transID,
                          @JacksonXmlProperty(localName = "InputList") InputList list,
                          @JacksonXmlProperty(localName = "HomeServer") HomeServer homeServer) {
        this.functionID = functionID;
        this.functionCategory = functionCategory;
        this.transID = transID;
        this.list = list;
        this.homeServer = homeServer;
    }
}
