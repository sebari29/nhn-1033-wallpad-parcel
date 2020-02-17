package com.wallpad.net.sample.timeinfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.wallpad.net.sample.vehicleparking.request.InputList;

@JacksonXmlRootElement(localName = "HNML")
public class ControlRequest {

    @JacksonXmlProperty(localName = "FunctionID")
    private String functionID;
    @JacksonXmlProperty(localName = "FunctionCategory")
    private String functionCategory;
    @JacksonXmlProperty(isAttribute = true, localName = "TransID")
    private String transID;

    @JsonCreator
    public ControlRequest(@JacksonXmlProperty(isAttribute = true, localName = "TransID") String transID,
                          @JacksonXmlProperty(localName = "FunctionID") String functionID,
                          @JacksonXmlProperty(localName = "FunctionCategory") String functionCategory) {
        this.functionID = functionID;
        this.functionCategory = functionCategory;
        this.transID = transID;
    }
}
