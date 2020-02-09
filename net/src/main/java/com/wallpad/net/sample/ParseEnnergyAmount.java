package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.energyamonut.request.ControlRequest;
import com.wallpad.net.sample.energyamonut.request.Data;
import com.wallpad.net.sample.energyamonut.request.HNMLEnnergyAmount;
import com.wallpad.net.sample.energyamonut.request.Input;
import com.wallpad.net.sample.energyamonut.request.InputList;
import com.wallpad.net.sample.energyamonut.response.HNMLEnnergyAmountResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseEnnergyAmount {
    public static void main(String[] args) {
        String string ="<HNML>\n" +
                "  <ControlResponse TransID=\"WP201308210942123\">\n" +
                "    <FunctionID>1F030106</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"1\">\n" +
                "      <Output size=\"2\">\n" +
                "        <Data name=\"CategoryType\">Electricity</Data>\n" +
                "        <Data name=\"Cost\">150000</Data>\n" +
                "      </Output>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";
        String string1 = "<HNML>\n" +
                "  <ControlResponse TransID=\"WP201308210942123\">\n" +
                "    <FunctionID>1F030106</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>1000</Result>\n" +
                "    <Exception>error message</Exception>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";
        try {
            XMLUtils<HNMLEnnergyAmount> utils = new XMLUtils<>();
            String transID = "WP201308210942123";
            String functionID = "1F030106";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("CategoryType","Electricity"));
            dataList.add(new Data("Amount","100"));
            String sizeInput = "3";
            Input input = new Input(sizeInput,dataList);
            InputList list = new InputList(input,sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory,list);
            HNMLEnnergyAmount hnmlEnnergyAmount = new HNMLEnnergyAmount(request);
            String stringRequest =  utils.convertObjectToXML(hnmlEnnergyAmount);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLEnnergyAmountResponse> xmlUtils = new XMLUtils<>();
        HNMLEnnergyAmountResponse hnmlEnnergyAmountResponse = xmlUtils.convertXMLToObject(string1, HNMLEnnergyAmountResponse.class);
        System.out.println("TransID :" + hnmlEnnergyAmountResponse.getControlResponse().getTransID());
        System.out.println("getFunctionID :" + hnmlEnnergyAmountResponse.getControlResponse().getFunctionID());
        System.out.println("getFunctionCategory :" + hnmlEnnergyAmountResponse.getControlResponse().getFunctionCategory());
        System.out.println("getResult :" + hnmlEnnergyAmountResponse.getControlResponse().getResult());
        if (hnmlEnnergyAmountResponse.getControlResponse().getException()== null) {
            int sizeOutput = Integer.parseInt(hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getSize());
            for (int i = 0; i < sizeOutput; i++) {
                System.out.println(hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getName() + "--" +
                        hnmlEnnergyAmountResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getValue());
            }
        }else {
            System.out.println(hnmlEnnergyAmountResponse.getControlResponse().getException().getValueException());
        }
    }
}
