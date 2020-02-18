package com.wallpad.net.sample.parcelnotification.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLParcelNotification {
    @JacksonXmlProperty(localName = "ControlRequest")
    public ControlRequest controlRequest;

//    @JsonCreator
//    public HNMLParcelNotification(@JacksonXmlProperty(localName = "ControlRequest") ControlRequest controlRequest) {
//        this.controlRequest = controlRequest;
//    }
}
