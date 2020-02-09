package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.vehicleparkinginfo.request.ControlRequest;
import com.wallpad.net.sample.vehicleparkinginfo.request.Data;
import com.wallpad.net.sample.vehicleparkinginfo.request.HNMLVehicleParkingInfo;
import com.wallpad.net.sample.vehicleparkinginfo.request.Input;
import com.wallpad.net.sample.vehicleparkinginfo.request.InputList;
import com.wallpad.net.sample.vehicleparkinginfo.response.HNMLVehicleParkingInfoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseVehicleParkingInfo {
    public static void main(String[] args) {
        String string ="<HNML>\n" +
                "  <ControlResponse TransID=\"PL201308210942123\">\n" +
                "    <FunctionID>1F03010A</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";
        try {
            XMLUtils<HNMLVehicleParkingInfo> utils = new XMLUtils<>();
            String transID = "PL201308210942123";
            String functionID = "1F030106";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("HomeID", "00000101010100"));
            dataList.add(new Data("CarNo", "2나5333"));
            dataList.add(new Data("CoordinateX", "12"));
            dataList.add(new Data("CoordinateY", "44"));
            dataList.add(new Data("CarLoc", "B1F 23"));
            dataList.add(new Data("ImgName", "B1F_1"));
            dataList.add(new Data("Time", "2012-31-11T01:11:55"));
            String sizeInput = "6";
            Input input = new Input(sizeInput, dataList);
            InputList list = new InputList(input, sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory, list);
            HNMLVehicleParkingInfo hnmlVehicleParkingInfo = new HNMLVehicleParkingInfo(request);
            String stringRequest = utils.convertObjectToXML(hnmlVehicleParkingInfo);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLVehicleParkingInfoResponse> xmlUtils = new XMLUtils<>();
        HNMLVehicleParkingInfoResponse object = xmlUtils.convertXMLToObject(string, HNMLVehicleParkingInfoResponse.class);
        System.out.println("TransID: "+object.getControlResponse().getTransID());
        System.out.println("FunctionID: "+object.getControlResponse().getFunctionID());
        System.out.println("FunctionCategory: "+object.getControlResponse().getFunctionCategory());
        System.out.println("Result: "+object.getControlResponse().getResult());
    }
}
