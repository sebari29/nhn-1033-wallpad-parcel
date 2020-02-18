package com.wallpad.net.sample.parcelnotification.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ControlRequest")
public class InputList {
    @JacksonXmlProperty(localName = "Input")
    private Input input;

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    private String _size;

//    @JsonCreator()
//    public InputList(@JacksonXmlProperty(localName = "Input") List<Input> input,
//                     @JacksonXmlProperty(isAttribute = true, localName = "size") String _size) {
//        this._size = _size;
//        this.input = input;
//    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public String get_size() {
        return _size;
    }

    public void set_size(String _size) {
        this._size = _size;
    }
}
