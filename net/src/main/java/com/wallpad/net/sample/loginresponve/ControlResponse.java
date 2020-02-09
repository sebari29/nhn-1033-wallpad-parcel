package com.wallpad.net.sample.loginresponve;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class ControlResponse {
    @JacksonXmlProperty(localName = "FunctionID")
    private String FunctionID;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "install_info_configure")
    private InfoConfigureInstall install_info_configure;

    @JacksonXmlProperty(localName = "TransID")
    private String TransID;

    @JacksonXmlProperty(localName = "FunctionCategory")
    private String FunctionCategory;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "device_configure")
    private DeviceConfigure device_configure;

    @JacksonXmlProperty(localName = "Result")
    private String Result;

    @JacksonXmlProperty(localName = "OutputList")
    private OutputList OutputList;

    public String getFunctionID() {
        return FunctionID;
    }

    public void setFunctionID(String functionID) {
        FunctionID = functionID;
    }

    public InfoConfigureInstall getInstall_info_configure() {
        return install_info_configure;
    }

    public void setInstall_info_configure(InfoConfigureInstall install_info_configure) {
        this.install_info_configure = install_info_configure;
    }

    public String getTransID() {
        return TransID;
    }

    public void setTransID(String transID) {
        TransID = transID;
    }

    public String getFunctionCategory() {
        return FunctionCategory;
    }

    public void setFunctionCategory(String functionCategory) {
        FunctionCategory = functionCategory;
    }

    public DeviceConfigure getDevice_configure() {
        return device_configure;
    }

    public void setDevice_configure(DeviceConfigure device_configure) {
        this.device_configure = device_configure;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public OutputList getOutputList() {
        return OutputList;
    }

    public void setOutputList(OutputList outputList) {
        OutputList = outputList;
    }

}
