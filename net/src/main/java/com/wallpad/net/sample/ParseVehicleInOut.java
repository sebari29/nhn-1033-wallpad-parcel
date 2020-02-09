package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.vehicleinout.request.ControlRequest;
import com.wallpad.net.sample.vehicleinout.request.HNMLVehicleInOut;
import com.wallpad.net.sample.vehicleinout.request.Param;
import com.wallpad.net.sample.vehicleinout.request.Sql;
import com.wallpad.net.sample.vehicleinout.response.HNMLVehicleInOutResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseVehicleInOut {

    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"WP20191230134128639\">\n" +
                "    <FunctionID>1F032001</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"1\">\n" +
                "      <Output size=\"4\">\n" +
                "        <Data name=\"CarNo\">80크9870</Data>\n" +
                "        <Data name=\"Direction\">In</Data>\n" +
                "        <Data name=\"Time\">2019-12-30T13:40:18</Data>\n" +
                "        <Data name=\"totalcount\">1</Data>\n" +
                "      </Output>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>";
        try {
            XMLUtils<HNMLVehicleInOut> xmlUtils = new XMLUtils<>();
            String transID = "WP20191230134128639";
            String functionID = "1F032001";
            String functionCategory = "Control";

            String idSQL = "d_car_inout_info_list_select_page";
            List<Param> list = new ArrayList<>();
            list.add(new Param("page", "top1", "6"));
            list.add(new Param("page", "top2", "0"));
            Sql sql = new Sql(idSQL, list);
            ControlRequest controlRequest = new ControlRequest(transID, functionID, functionCategory, sql);
            HNMLVehicleInOut hnmlVehicleInOut = new HNMLVehicleInOut(controlRequest);
            String objectRequest = xmlUtils.convertObjectToXML(hnmlVehicleInOut);
            System.out.println(objectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XMLUtils<HNMLVehicleInOutResponse> xmlUtils = new XMLUtils<>();
        HNMLVehicleInOutResponse hnmlInquiryResponse = xmlUtils.convertXMLToObject(string, HNMLVehicleInOutResponse.class);
        System.out.println("TransID :" + hnmlInquiryResponse.getControlResponse().getTransID());
        System.out.println("getFunctionID :" + hnmlInquiryResponse.getControlResponse().getFunctionID());
        System.out.println("getFunctionCategory :" + hnmlInquiryResponse.getControlResponse().getFunctionCategory());
        System.out.println("getResult :" + hnmlInquiryResponse.getControlResponse().getResult());
        int sizeListOutput = Integer.parseInt(hnmlInquiryResponse.getControlResponse().getOutputList()._size);
        for (int i = 0; i < sizeListOutput; i++) {
            int sizeOutput = Integer.parseInt(hnmlInquiryResponse.getControlResponse().getOutputList().Output.get(i).getSize());
            for (int j = 0; j < sizeOutput; j++) {
                System.out.println(hnmlInquiryResponse.getControlResponse().getOutputList().Output.get(i).getData().get(j).getName() + "-" +
                        hnmlInquiryResponse.getControlResponse().getOutputList().Output.get(i).getData().get(j).getValue());
            }
        }
    }
}
