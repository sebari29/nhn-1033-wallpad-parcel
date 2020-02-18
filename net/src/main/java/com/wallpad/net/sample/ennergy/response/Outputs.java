package com.wallpad.net.sample.ennergy.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Outputs")
public class Outputs {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Output")
    private List<Output> Output;
    @JacksonXmlProperty(localName = "size")
    private String _size;
    @JacksonXmlProperty(localName = "name")
    private String _name;

    public List<Output> getOutput() {
        return Output;
    }

    public void setOutput(List<Output> output) {
        Output = output;
    }

    public String get_size() {
        return _size;
    }

    public void set_size(String _size) {
        this._size = _size;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }


}

