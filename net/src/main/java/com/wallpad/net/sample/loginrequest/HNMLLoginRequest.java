package com.wallpad.net.sample.loginrequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLLoginRequest {
    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

    @JsonCreator
    public HNMLLoginRequest(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
        this.controlRequest = controlRequest;
    }
}
