package com.wallpad.net.sample.loginresponve;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLLogin {

    @JsonGetter(value = "ControlResponse")
    public com.wallpad.net.sample.loginresponve.ControlResponse getControlResponse() {
        return ControlResponse;
    }

    @JacksonXmlProperty(localName = "ControlResponse")
    private ControlResponse ControlResponse;
}
