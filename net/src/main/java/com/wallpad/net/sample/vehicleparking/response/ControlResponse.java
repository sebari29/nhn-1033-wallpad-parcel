package com.wallpad.net.sample.vehicleparking.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "HNML")
public class ControlResponse {
    @JacksonXmlProperty(localName = "FunctionID")
    private String functionID;
    @JacksonXmlProperty(localName = "FunctionCategory")
    private String functionCategory;
    @JacksonXmlProperty(localName = "Result")
    private String result;
    @JacksonXmlProperty(isAttribute = true, localName = "TransID")
    private String transID;
    @JacksonXmlProperty(localName = "OutputList")
    private OutputList outputList;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public OutputList getOutputList() {
        return outputList;
    }

    public void setOutputList(OutputList outputList) {
        this.outputList = outputList;
    }


}
