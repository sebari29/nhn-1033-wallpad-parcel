package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.weather.request.ControlRequest;
import com.wallpad.net.sample.weather.request.Data;
import com.wallpad.net.sample.weather.request.HNMLWeather;
import com.wallpad.net.sample.weather.request.Input;
import com.wallpad.net.sample.weather.request.InputList;
import com.wallpad.net.sample.weather.response.HNMLWeatherResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseWeather {
    public static void main(String[] args) {
        String string = "<HNML>  <ControlResponse TransID=\"WP20200113175914966\">    \n" +
                "<FunctionID>1F500301</FunctionID>    \n" +
                "<FunctionCategory>Control</FunctionCategory>    <Result>0000</Result>    \n" +
                "<OutputList size=\"1\">    <Output size=\"19\">        \n" +
                "<Data name=\"Temp\">4.55</Data>        <Data name=\"Humi\">44</Data>        \n" +
                "<Data name=\"WindDir\">260</Data>        <Data name=\"WindSpeed\">1.5</Data>        \n" +
                "<Data name=\"Main\">Clear</Data>        <Data name=\"Id\">800</Data>        \n" +
                "<Data name=\"Pm10\">26</Data>        <Data name=\"Pm25\">34</Data>        \n" +
                "<Data name=\"TempMin\">3</Data>        <Data name=\"TempMax\">6</Data>        \n" +
                "<Data name=\"Forecast1_Id\">800</Data>        <Data name=\"Forecast2_Id\">804</Data>        \n" +
                "<Data name=\"Forecast3_Id\">804</Data>        <Data name=\"Forecast1_Min\">2.97</Data>        \n" +
                "<Data name=\"Forecast1_Max\">5.39</Data>        <Data name=\"Forecast2_Min\">2.77</Data>        \n" +
                "<Data name=\"Forecast2_Max\">5.72</Data>        <Data name=\"Forecast3_Min\">3.23</Data>        \n" +
                "<Data name=\"Forecast3_Max\">6.33</Data>    \n" +
                "</Output>    </OutputList>  </ControlResponse></HNML>\n";
        try {
            XMLUtils<HNMLWeather> utils = new XMLUtils<>();
            String transID = "WP20191227152258118";
            String functionID = "1F500301";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("Complex", "0000"));
            dataList.add(new Data("Dong", "0110"));
            dataList.add(new Data("Ho", "0101"));
            String sizeInput = "3";
            String nameInput = "WeatherList";
            Input input = new Input(sizeInput,nameInput ,dataList);
            InputList list = new InputList(input, sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory, list);
            HNMLWeather hnmlVehicleParkingInfo = new HNMLWeather(request);
            String stringRequest = utils.convertObjectToXML(hnmlVehicleParkingInfo);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNMLWeatherResponse> xmlUtils = new XMLUtils<>();
        HNMLWeatherResponse hnmlEnnergyAmountResponse = xmlUtils.convertXMLToObject(string, HNMLWeatherResponse.class);
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
