package com.wallpad.net.sample.parcelInfoInquiry.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLParcelInfoInquiry {

    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

    @JsonCreator
    public HNMLParcelInfoInquiry(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
        this.controlRequest = controlRequest;
    }
}
