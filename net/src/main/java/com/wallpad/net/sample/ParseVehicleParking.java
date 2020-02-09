package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.vehicleparking.request.ControlRequest;
import com.wallpad.net.sample.vehicleparking.request.Data;
import com.wallpad.net.sample.vehicleparking.request.HNMLVehicleParking;
import com.wallpad.net.sample.vehicleparking.request.Input;
import com.wallpad.net.sample.vehicleparking.request.InputList;
import com.wallpad.net.sample.vehicleparking.response.HNMLVehicleParkingResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseVehicleParking {
    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"WP201308210942123\">\n" +
                "    <FunctionID>1F03010A</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"1\">\n" +
                "      <Output size=\"7\">\n" +
                "        <Data name=\"HomeID\">00000101010100</Data>\n" +
                "        <Data name=\"CarNo\">2나5333</Data>\n" +
                "        <Data name=\"CoordinateX\">12</Data>\n" +
                "        <Data name=\"CoordinateY\">44</Data>\n" +
                "        <Data name=\"CarLoc\">B1F 23</Data>\n" +
                "        <Data name=\"ImgName\">B1F_1</Data> \n" +
                "        <Data name=\"Time\">2012-31-11T01:11:55</Data>\n" +
                "      </Output>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";
        try {
            XMLUtils<HNMLVehicleParking> utils = new XMLUtils<>();
            String transID = "WP201308210942123";
            String functionID = "1F030106";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("HomeID", "00000101010100"));
            String sizeInput = "1";
            Input input = new Input(sizeInput, dataList);
            InputList list = new InputList(input, sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory, list);
            HNMLVehicleParking hnmlVehicleParking = new HNMLVehicleParking(request);
            String stringRequest = utils.convertObjectToXML(hnmlVehicleParking);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLVehicleParkingResponse> xmlUtils = new XMLUtils<>();
        HNMLVehicleParkingResponse hnmlEnnergyAmountResponse = xmlUtils.convertXMLToObject(string, HNMLVehicleParkingResponse.class);
        System.out.println("TransID :" + hnmlEnnergyAmountResponse.getControlResponse().getTransID());
        System.out.println("getFunctionID :" + hnmlEnnergyAmountResponse.getControlResponse().getFunctionID());
        System.out.println("getFunctionCategory :" + hnmlEnnergyAmountResponse.getControlResponse().getFunctionCategory());
        System.out.println("getResult :" + hnmlEnnergyAmountResponse.getControlResponse().getResult());
        int sizeOutput = Integer.parseInt(hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getSize());
        for (int i = 0; i < sizeOutput; i++) {
            System.out.println(hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getName() + "--" +
                    hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getValue());
        }
    }
}
