package com.wallpad.net.sample.loginresponve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "ControlResponse")
public class DeviceConfigure {

    @JacksonXmlProperty(localName = "sitename")
    private String sitename;

    @JacksonXmlProperty(localName = "version")
    private String version;

    @JacksonXmlProperty(localName = "device")
    private List<Device> listDevice = new ArrayList<>();

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Device> getListDevice() {
        return listDevice;
    }

    public void setListDevice(List<Device> listDevice) {
        this.listDevice = listDevice;
    }


}
