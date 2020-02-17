package com.wallpad.net.sample.vehicleparkinginfo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class ControlRequest {

    @JacksonXmlProperty(localName = "FunctionID")
    private String functionID;
    @JacksonXmlProperty(localName = "FunctionCategory")
    private String functionCategory;
    @JacksonXmlProperty(isAttribute = true, localName = "TransID")
    private String transID;
    @JacksonXmlProperty(localName = "InputList")
    private InputList listInput;

    @JsonCreator
    public ControlRequest(@JacksonXmlProperty(isAttribute = true, localName = "TransID") String transID,
                          @JacksonXmlProperty(localName = "FunctionID") String functionID,
                          @JacksonXmlProperty(localName = "FunctionCategory") String functionCategory,
                          @JacksonXmlProperty(localName = "InputList") InputList list) {
        this.functionID = functionID;
        this.functionCategory = functionCategory;
        this.transID = transID;
        this.listInput = list;
    }
}
