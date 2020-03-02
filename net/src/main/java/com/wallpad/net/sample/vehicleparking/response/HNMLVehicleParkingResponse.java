package com.wallpad.net.sample.vehicleparking.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HNML")
public class HNMLVehicleParkingResponse {
    @JacksonXmlProperty(localName = "ControlResponse")
    private ControlResponse ControlResponseObject;


    public ControlResponse getControlResponse() {
        return ControlResponseObject;
    }

    public void setControlResponse(ControlResponse ControlResponseObject) {
        this.ControlResponseObject = ControlResponseObject;
    }
}