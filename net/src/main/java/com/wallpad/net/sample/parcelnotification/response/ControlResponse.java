package com.wallpad.net.sample.parcelnotification.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class ControlResponse {
    @JacksonXmlProperty(localName = "FunctionID")
    private String FunctionID;
    @JacksonXmlProperty(localName = "FunctionCategory")
    private String FunctionCategory;
    @JacksonXmlProperty(localName = "Result")
    private String Result;
    @JacksonXmlProperty(localName = "TransID")
    private String _TransID;

    public String getFunctionID() {
        return FunctionID;
    }

    public String getFunctionCategory() {
        return FunctionCategory;
    }

    public String getResult() {
        return Result;
    }

    public String get_TransID() {
        return _TransID;
    }

    public void setFunctionID(String FunctionID) {
        this.FunctionID = FunctionID;
    }

    public void setFunctionCategory(String FunctionCategory) {
        this.FunctionCategory = FunctionCategory;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public void set_TransID(String _TransID) {
        this._TransID = _TransID;
    }
}
