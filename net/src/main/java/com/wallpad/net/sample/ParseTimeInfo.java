package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.timeinfo.request.ControlRequest;
import com.wallpad.net.sample.timeinfo.request.HNMLTimeInfo;
import com.wallpad.net.sample.timeinfo.response.HNMLTimeInfoResponse;

import java.io.IOException;

public class ParseTimeInfo {
    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"PW201308210942123\">\n" +
                "    <FunctionID>1107001B</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"1\">\n" +
                "      <Output size=\"2\" name=\"Timestamp\">\n" +
                "        <Data name=\"Timestamp\">2013-12-31T12:00:00</Data>                          \n" +
                "      <Data name=\"Version\">1.1.0</Data>\n" +
                "      </Output>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";
        try {
            XMLUtils<HNMLTimeInfo> utils = new XMLUtils<>();
            String transID = "PW201308210942123";
            String functionID = "1107001B";
            String functionCategory = "Control";
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory);
            HNMLTimeInfo hnmlTimeInfo = new HNMLTimeInfo(request);
            String stringRequest = utils.convertObjectToXML(hnmlTimeInfo);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLTimeInfoResponse> xmlUtils = new XMLUtils<>();
        HNMLTimeInfoResponse hnmlInquiryResponse = xmlUtils.convertXMLToObject(string, HNMLTimeInfoResponse.class);
        System.out.println("TransID :" + hnmlInquiryResponse.getControlResponse().getTransID());
        System.out.println("getFunctionID :" + hnmlInquiryResponse.getControlResponse().getFunctionID());
        System.out.println("getFunctionCategory :" + hnmlInquiryResponse.getControlResponse().getFunctionCategory());
        System.out.println("getResult :" + hnmlInquiryResponse.getControlResponse().getResult());
        int sizeOutput = Integer.parseInt(hnmlInquiryResponse.getControlResponse().getOutputList().getOutput().getSize());
        System.out.println("Output name: "+ hnmlInquiryResponse.getControlResponse().getOutputList().getOutput().getName());
        for (int i = 0; i < sizeOutput; i++) {
            System.out.println(hnmlInquiryResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getName() + "--" +
                    hnmlInquiryResponse.getControlResponse().getOutputList().getOutput().getData().get(i).getValue());
        }
    }
}
