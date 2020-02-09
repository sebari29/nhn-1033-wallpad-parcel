package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.parcelInfoInquiry.request.ControlRequest;
import com.wallpad.net.sample.parcelInfoInquiry.request.HNMLParcelInfoInquiry;
import com.wallpad.net.sample.parcelInfoInquiry.request.Param;
import com.wallpad.net.sample.parcelInfoInquiry.request.Sql;
import com.wallpad.net.sample.parcelInfoInquiry.response.HNMLInquiryResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseInfoInquiry {
    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"WP20191230134316105\">\n" +
                "    <FunctionID>1F033001</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"1\">\n" +
                "      <Output size=\"7\">\n" +
                "        <Data name=\"ArriveTime\">2019-12-30T13:42:24</Data>\n" +
                "        <Data name=\"ReceiveTime\" />\n" +
                "        <Data name=\"EventType\">0</Data>\n" +
                "        <Data name=\"Name\" />\n" +
                "        <Data name=\"id\">000000000000</Data>\n" +
                "        <Data name=\"BoxNo\">23</Data>\n" +
                "        <Data name=\"totalcount\">1</Data>\n" +
                "      </Output>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>\n";


        try {
            XMLUtils<HNMLParcelInfoInquiry> xmlUtils = new XMLUtils<>();
            String transID = "WP20191230134316105";
            String functionID = "1F033001";
            String functionCategory = "Control";

            String idSQL = "d_delivery_info_list_select_page";
            List<Param> list = new ArrayList<>();
            list.add(new Param("page", "top1", "6"));
            list.add(new Param("page", "top2", "0"));
            Sql sql = new Sql(idSQL, list);
            ControlRequest controlRequest = new ControlRequest(transID, functionID, functionCategory, sql);
            HNMLParcelInfoInquiry hnmlParcelInfoInquiry = new HNMLParcelInfoInquiry(controlRequest);
            String objectRequest = xmlUtils.convertObjectToXML(hnmlParcelInfoInquiry);
            System.out.println(objectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XMLUtils<HNMLInquiryResponse> xmlUtils = new XMLUtils<>();
        HNMLInquiryResponse hnmlInquiryResponse = xmlUtils.convertXMLToObject(string, HNMLInquiryResponse.class);
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
