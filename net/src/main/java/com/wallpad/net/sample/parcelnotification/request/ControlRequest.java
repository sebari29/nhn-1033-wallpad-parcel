package com.wallpad.net.sample.parcelnotification.request;

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

//    @JsonCreator
//    public ControlRequest(@JacksonXmlProperty(isAttribute = true, localName = "TransID") String transID,
//                          @JacksonXmlProperty(localName = "FunctionID") String functionID,
//                          @JacksonXmlProperty(localName = "FunctionCategory") String functionCategory,
//                          @JacksonXmlProperty(localName = "InputList") InputList list) {
//        this.functionID = functionID;
//        this.functionCategory = functionCategory;
//        this.transID = transID;
//        this.listInput = list;
//    }

    public String getFunctionID() {
        return functionID;
    }

    public void setFunctionID(String functionID) {
        this.functionID = functionID;
    }

    public String getFunctionCategory() {
        return functionCategory;
    }

    public void setFunctionCategory(String functionCategory) {
        this.functionCategory = functionCategory;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public InputList getListInput() {
        return listInput;
    }

    public void setListInput(InputList listInput) {
        this.listInput = listInput;
    }
}