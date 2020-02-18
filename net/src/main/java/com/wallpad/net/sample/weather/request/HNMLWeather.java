package com.wallpad.net.sample.weather.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLWeather {
    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

    @JsonCreator
    public HNMLWeather(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
        this.controlRequest = controlRequest;
    }
}
