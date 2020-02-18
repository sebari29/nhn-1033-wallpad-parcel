package com.wallpad.net.sample.parcelInfoInquiry.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "OutputList")
public class OutputList {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Output")
    public ArrayList<Output> Output = new ArrayList<>();

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    public String _size;
}

