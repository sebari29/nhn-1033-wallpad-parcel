package com.wallpad.net.sample.parcelInfoInquiry.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "sql")
public class Param {
    @JacksonXmlProperty(localName = "type")
    private String type;
    @JacksonXmlProperty(localName = "column")
    private String column;
    @JacksonXmlProperty(localName = "value")
    private String value;

    @JsonCreator
    public Param(@JacksonXmlProperty(localName = "type") String type,
                 @JacksonXmlProperty(localName = "column") String column,
                 @JacksonXmlProperty(localName = "value") String value) {
        this.type = type;
        this.column = column;
        this.value = value;
    }
}
