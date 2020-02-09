package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.parcelnotification.request.ControlRequest;
import com.wallpad.net.sample.parcelnotification.request.Data;
import com.wallpad.net.sample.parcelnotification.request.HNMLParcelNotification;
import com.wallpad.net.sample.parcelnotification.request.Input;
import com.wallpad.net.sample.parcelnotification.request.InputList;
import com.wallpad.net.sample.parcelnotification.response.HNMLParcelResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseParcelnfo {
    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"danji20191230134224647\">\n" +
                "    <FunctionID>1F03010C</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "  </ControlResponse>\n" +
                "</HNML>";


        try {
            XMLUtils<HNMLParcelNotification> utils = new XMLUtils<>();
            String transID = "danji20191230134224647";
            String functionID = "1F03010C";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("Time","2019-12-30T13:42:24"));
            dataList.add(new Data("EventType","Arrive"));
            dataList.add(new Data("Name",""));
            dataList.add(new Data("Complex","0000"));
            dataList.add(new Data("Dong","0101"));
            dataList.add(new Data("Ho","2703"));
            dataList.add(new Data("BoxNo","23"));
            dataList.add(new Data("id","000000000000"));
            String sizeInput = "7";
            Input input = new Input(sizeInput,dataList);
            InputList list = new InputList(input,sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory,list);
            HNMLParcelNotification hnmlParcelNotification = new HNMLParcelNotification(request);
            String stringRequest =  utils.convertObjectToXML(hnmlParcelNotification);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLParcelResponse> xmlUtils = new XMLUtils<>();
        HNMLParcelResponse object = xmlUtils.convertXMLToObject(string, HNMLParcelResponse.class);
        System.out.println("TransID: "+object.getControlResponse().get_TransID());
        System.out.println("FunctionID: "+object.getControlResponse().getFunctionID());
        System.out.println("FunctionCategory: "+object.getControlResponse().getFunctionCategory());
        System.out.println("Result: "+object.getControlResponse().getResult());
    }
}
