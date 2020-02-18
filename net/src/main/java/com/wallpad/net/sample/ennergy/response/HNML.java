package com.wallpad.net.sample.ennergy.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "HNML")
public class HNML {
    @JacksonXmlProperty(localName = "ControlResponse")
    private ControlResponse controlResponseObject;

    public ControlResponse getControlResponseObject() {
        return controlResponseObject;
    }

    public void setControlResponseObject(ControlResponse controlResponseObject) {
        this.controlResponseObject = controlResponseObject;
    }


}
