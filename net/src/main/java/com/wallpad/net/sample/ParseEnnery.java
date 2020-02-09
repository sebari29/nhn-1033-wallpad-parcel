package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.ennergy.request.ControlRequest;
import com.wallpad.net.sample.ennergy.request.Data;
import com.wallpad.net.sample.ennergy.request.HNMLEnergyRequest;
import com.wallpad.net.sample.ennergy.request.Input;
import com.wallpad.net.sample.ennergy.request.InputList;
import com.wallpad.net.sample.ennergy.response.HNML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseEnnery {
    public static void main(String[] args) {
        String string = "<HNML>\n" +
                "  <ControlResponse TransID=\"WP20191231143033917\">\n" +
                "    <FunctionID>1F030105</FunctionID>\n" +
                "    <FunctionCategory>Control</FunctionCategory>\n" +
                "    <Result>0000</Result>\n" +
                "    <OutputList size=\"2\">\n" +
                "      <Output size=\"9\" name=\"HomeInfo\">\n" +
                "        <Data name=\"HomeID\">000001011402</Data>\n" +
                "        <Data name=\"StartTime\">2019-12-01</Data>\n" +
                "        <Data name=\"EndTime\">2019-12-31</Data>\n" +
                "        <Data name=\"CategoryType\">Electricity</Data>\n" +
                "        <Data name=\"QueryType\">Day</Data>\n" +
                "        <Data name=\"TotalAverage\">0.69</Data>\n" +
                "        <Data name=\"SameAverage\">2.92</Data>\n" +
                "        <Data name=\"MyAverage\">2.92</Data>\n" +
                "        <Data name=\"TodayUsed\">87.70</Data>\n" +
                "      </Output>\n" +
                "      <Outputs size=\"30\" name=\"Electricity\">\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-01T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-01T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">1.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-02T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-02T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-03T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-03T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-04T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-04T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">4.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-05T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-05T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">5.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-06T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-06T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-07T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-07T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.20</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-08T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-08T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">0.40</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-09T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-09T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.80</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-10T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-10T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.70</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-11T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-11T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.20</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-12T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-12T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.50</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-13T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-13T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">0.90</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-14T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-14T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-15T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-15T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-16T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-16T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-17T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-17T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.50</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-18T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-18T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.60</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-19T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-19T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">0.70</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-20T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-20T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.50</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-21T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-21T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">7.40</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-22T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-22T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">4.10</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-23T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-23T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-24T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-24T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">1.00</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-25T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-25T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">1.80</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-26T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-26T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">7.20</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-27T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-27T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">5.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-28T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-28T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">1.90</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-29T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-29T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">2.70</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "        <Output size=\"4\">\n" +
                "          <Data name=\"StartTime\">2019-12-30T00:00:00</Data>\n" +
                "          <Data name=\"EndTime\">2019-12-30T23:59:59</Data>\n" +
                "          <Data name=\"Amount\">3.30</Data>\n" +
                "          <Data name=\"Unit\">KWh</Data>\n" +
                "        </Output>\n" +
                "      </Outputs>\n" +
                "    </OutputList>\n" +
                "  </ControlResponse>\n" +
                "</HNML>";
        try {
            XMLUtils<HNMLEnergyRequest> utils = new XMLUtils<>();
            String transID = "WP20191231143033917";
            String functionID = "1F030105";
            String functionCategory = "Control";
            String sizeInputList = "1";
            List<Data> dataList = new ArrayList<>();
            dataList.add(new Data("HomeID", "00000101140200"));
            dataList.add(new Data("StartTime", "2019-12-01T14:30:33"));
            dataList.add(new Data("EndTime", "2019-12-31T14:30:33"));
            dataList.add(new Data("CategoryType", "Electricity"));
            dataList.add(new Data("QueryType", "Day"));
            String sizeInput = "5";
            String idInput = "0";
            Input input = new Input(sizeInput, idInput, dataList);

            InputList list = new InputList(input, sizeInputList);
            ControlRequest request = new ControlRequest(transID, functionID, functionCategory, list);
            HNMLEnergyRequest hnmlEnergyRequest = new HNMLEnergyRequest(request);
            String stringRequest = utils.convertObjectToXML(hnmlEnergyRequest);
            System.out.println(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLUtils<HNML> utils = new XMLUtils<>();
        HNML bean = utils.convertXMLToObject(string, HNML.class);
        System.out.println(bean.getControlResponseObject().getTransID());
        System.out.println(bean.getControlResponseObject().getFunctionID());
        System.out.println(bean.getControlResponseObject().getFunctionCategory());
        System.out.println("size:" + bean.getControlResponseObject().getOutputList().getOutputsObject().get_size());
        System.out.println("name:" + bean.getControlResponseObject().getOutputList().getOutputsObject().get_name());
        int sizeOutputList = Integer.parseInt(bean.getControlResponseObject().getOutputList().getOutputObject().get_size());
        System.out.println("----LIST DATA OUTPUT-----");
        for (int i = 0; i < sizeOutputList; i++) {
            System.out.println(bean.getControlResponseObject().getOutputList().getOutputObject().getData().get(i).getName() + "--"
                    + bean.getControlResponseObject().getOutputList().getOutputObject().getData().get(i).getValueData());
        }
        int sizeOutputsList = Integer.parseInt(bean.getControlResponseObject().getOutputList().getOutputsObject().get_size());
        System.out.println("----LIST DATA OUTPUTS-----");
        for (int i = 0; i < sizeOutputsList; i++) {
            int sizeOutput = Integer.parseInt(bean.getControlResponseObject().getOutputList().getOutputsObject().getOutput().get(i).get_size());
            for (int j = 0; j < sizeOutput; j++) {
                System.out.println(bean.getControlResponseObject().getOutputList().getOutputsObject().getOutput().get(i).getData().get(j).getName() + "--" +
                        bean.getControlResponseObject().getOutputList().getOutputsObject().getOutput().get(i).getData().get(j).getValueData());
            }

        }
//            for (int i = 0; i < size; i++) {
//                System.out.println("value" + i + ":" + bean.getControlResponseObject().getOutputList().getOutputsObject().getOutput().get(i).get_name() + "-" + bean.getControlResponseObject().getOutputList().getOutputObject().get(i).get_size());
//            }
//            int sizeList = Integer.parseInt(bean.controlResponseObject.outputList.otputsObject._size);
//            for (int i = 0; i < sizeList; i++) {
//                int sizeOutPutItem = Integer.parseInt(bean.controlResponseObject.outputList.otputsObject.Output.get(i)._size);
//                System.out.println(sizeOutPutItem);
//                for (int j = 0; j < sizeOutPutItem; j++) {
//                    System.out.println("value output" + j + ":" + bean.controlResponseObject.outputList.otputsObject.Output.get(i).data.get(j).name + "-" + bean.controlResponseObject.outputList.otputsObject.Output.get(i).data.get(j).valueData);
//                }
//            }
    }
}
