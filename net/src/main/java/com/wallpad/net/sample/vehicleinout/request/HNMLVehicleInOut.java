package com.wallpad.net.sample.vehicleinout.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLVehicleInOut {

    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

    @JsonCreator
    public HNMLVehicleInOut(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
        this.controlRequest = controlRequest;
    }
}
