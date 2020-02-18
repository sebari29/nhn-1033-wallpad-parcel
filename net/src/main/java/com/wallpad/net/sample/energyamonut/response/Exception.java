package com.wallpad.net.sample.energyamonut.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "ControlResponse")
public class Exception {
    @JacksonXmlText
    private String valueException;

    public String getValueException() {
        return valueException;
    }

    public void setValueException(String valueException) {
        this.valueException = valueException;
    }


}
