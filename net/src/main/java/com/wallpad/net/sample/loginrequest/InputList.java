package com.wallpad.net.sample.loginrequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ControlRequest")
public class InputList {
    @JacksonXmlProperty(localName = "Input")
    public Input input;

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    public String _size;

    @JsonCreator()
    public InputList(@JacksonXmlProperty(localName = "Input") Input input,
                     @JacksonXmlProperty(isAttribute = true, localName = "size") String _size) {
        this._size = _size;
        this.input = input;
    }
}
