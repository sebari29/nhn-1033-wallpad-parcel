package com.wallpad.net.sample.energyamonut.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLEnnergyAmount {
    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

    @JsonCreator
    public HNMLEnnergyAmount(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
        this.controlRequest = controlRequest;
    }
}
