package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.loginrequest.ControlRequest;
import com.wallpad.net.sample.loginrequest.Data;
import com.wallpad.net.sample.loginrequest.HNMLLoginRequest;
import com.wallpad.net.sample.loginrequest.Input;
import com.wallpad.net.sample.loginrequest.InputList;
import com.wallpad.net.sample.loginresponve.HNMLLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleXMLParse {
    public static void main(String[] args) {
        String s = "<HNML>  <ControlResponse TransID=\"WP20160101000008070\">    <FunctionID>11070001</FunctionID>    <FunctionCategory>Control</FunctionCategory>    <Result>0000</Result>    <OutputList size=\"2\"><Output size=\"1\" name=\"Timestamp\">  <Data name=\"Timestamp\">2020-01-08T18:01:14</Data></Output><Output size=\"7\" name=\"BasicInfo\" id=\"0\">  <Data name=\"IPAddress\">10.9.1.3</Data>  <Data name=\"Subnet\">255.0.0.0</Data>  <Data name=\"Gateway\">10.0.0.1</Data>  <Data name=\"Gateway\"></Data>  <Data name=\"Gateway\"></Data>  <Data name=\"HomeID\">000001090105</Data>  <Data name=\"FtpID\">kdone</Data>  <Data name=\"FtpPassword\">##KDnetworkER1</Data>  <Data name=\"FtpPort\">7001</Data></Output>    </OutputList><device_configure sitename=\"GASAN\" version=\"1.2\"><device size=\"5\" name=\"RoomCon\"><Data name=\"device_no\">1</Data><Data name=\"device_type\">54</Data><Data name=\"device_name\">방1</Data><Data name=\"group_id\">0</Data><Data name=\"sub_id\">1</Data></device><device size=\"5\" name=\"RoomCon\"><Data name=\"device_no\">2</Data><Data name=\"device_type\">54</Data><Data name=\"device_name\">방2_1</Data><Data name=\"group_id\">0</Data><Data name=\"sub_id\">2</Data></device><device size=\"5\" name=\"Gas\"><Data name=\"device_no\">11</Data><Data name=\"device_type\">18</Data><Data name=\"device_name\">주방</Data><Data name=\"group_id\">0</Data><Data name=\"sub_id\">1</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">21</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명1</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">1</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">22</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명2</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">2</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">23</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명3</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">3</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">24</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명4</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">4</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">25</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명5</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">5</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">26</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명6</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">6</Data></device><device size=\"5\" name=\"Light\"><Data name=\"device_no\">27</Data><Data name=\"device_type\">14</Data><Data name=\"device_name\">거실@조명7</Data><Data name=\"group_id\">1</Data><Data name=\"sub_id\">7</Data></device><device size=\"5\" name=\"BatchBreaker\"><Data name=\"device_no\">41</Data><Data name=\"device_type\">51</Data><Data name=\"device_name\">현관</Data><Data name=\"group_id\">0</Data><Data name=\"sub_id\">1</Data></device><device size=\"8\" name=\"SENSOR\"><Data name=\"gas\">1</Data><Data name=\"fire\">1</Data><Data name=\"lamp\">1</Data><Data name=\"secu1\">1</Data><Data name=\"secu2\">1</Data><Data name=\"secu3\">0</Data><Data name=\"secu4\">0</Data><Data name=\"ext2\">0</Data></device></device_configure><install_info_configure device=\"Wallpad\" sitename=\"Daewoo-Wonju-Taejang\" version=\"1.2\"><Install size=\"9\" name=\"Call\"><Data use=\"Yes\" name=\"doorphone\" /><Data use=\"Yes\" name=\"guardroom\" /><Data use=\"Yes\" name=\"neighbor\" /><Data use=\"Yes\" name=\"pstn\" /><Data use=\"Yes\" name=\"subphone\" /><Data use=\"Yes\" name=\"call_list\" /><Data use=\"Yes\" name=\"phonebook\" /><Data use=\"Yes\" name=\"lobby\" /><Data use=\"No\" name=\"interphone\" /></Install><Install size=\"14\" name=\"Device\"><Data use=\"No\" name=\"doorlock\" /><Data use=\"Yes\" name=\"elevator\" /><Data use=\"Yes\" name=\"light\" /><Data use=\"Yes\" name=\"ventilator\" /><Data use=\"Yes\" name=\"gasvalve\" /><Data use=\"Yes\" name=\"heater\" /><Data use=\"No\" name=\"aircon\" /><Data use=\"No\" name=\"curtain\" /><Data use=\"Yes\" name=\"batchbreak\" /><Data use=\"No\" name=\"extsecurity\" /><Data use=\"No\" name=\"boiler\" /><Data use=\"No\" name=\"remote\" /><Data use=\"Yes\" name=\"standbypower\" /><Data use=\"No\" name=\"amr\" /></Install><Install size=\"20\" name=\"Info\"><Data use=\"Yes\" name=\"visitor\" /><Data use=\"Yes\" name=\"notice\" /><Data use=\"Yes\" name=\"ems\" /><Data use=\"Yes\" name=\"cctv\" /><Data use=\"No\" name=\"manage_fee\" /><Data use=\"No\" name=\"vote\" /><Data use=\"No\" name=\"repair\" /><Data use=\"No\" name=\"ucity\" /><Data use=\"Yes\" name=\"parcel\" /><Data use=\"Yes\" name=\"parking\" /><Data use=\"No\" name=\"photo_album\" /><Data use=\"Yes\" name=\"memo\" /><Data use=\"Yes\" name=\"schedule\" /><Data use=\"Yes\" name=\"emergency\" /><Data use=\"No\" name=\"movie\" /><Data use=\"No\" name=\"music\" /><Data use=\"No\" name=\"parklocation\" /><Data use=\"Yes\" name=\"eleccharge\" /><Data use=\"Yes\" name=\"visit_reserve\" /><Data use=\"Yes\" name=\"onepass_parking\" /></Install><Install size=\"12\" name=\"Setup\"><Data use=\"Yes\" name=\"mode\" /><Data use=\"Yes\" name=\"security\" /><Data use=\"Yes\" name=\"password\" /><Data use=\"Yes\" name=\"bell\" /><Data use=\"Yes\" name=\"screen\" /><Data use=\"Yes\" name=\"sms\" /><Data use=\"Yes\" name=\"auto_notice\" /><Data use=\"Yes\" name=\"user_init\" /><Data use=\"Yes\" name=\"time\" /><Data use=\"Yes\" name=\"ars\" /><Data use=\"Yes\" name=\"rfcard\" /><Data use=\"Yes\" name=\"etc_setting\" /></Install><Install size=\"10\" name=\"Link\"><Data use=\"Yes\" name=\"message\" /><Data use=\"Yes\" name=\"mode\" /><Data use=\"Yes\" name=\"visitor\" /><Data use=\"Yes\" name=\"ems\" /><Data use=\"Yes\" name=\"elevator\" /><Data use=\"No\" name=\"ucity\" /><Data use=\"No\" name=\"batchbreak\" /><Data use=\"No\" name=\"emergency\" /><Data use=\"No\" name=\"callList\" /><Data use=\"No\" name=\"parcel\" /></Install><Install size=\"6\" name=\"EMS\"><Data use=\"Yes\" name=\"electricity\" /><Data use=\"Yes\" name=\"gas\" /><Data use=\"Yes\" name=\"water\" /><Data use=\"Yes\" name=\"hotwater\" /><Data use=\"Yes\" name=\"heating\" /><Data use=\"Yes\" name=\"cooling\" /></Install><Install size=\"3\" name=\"ONEPASS\"><Data use=\"twowinscomm\" name=\"companyname\" /><Data use=\"660\" name=\"width\" /><Data use=\"495\" name=\"height\" /></Install><Install size=\"2\" name=\"ELECCHARGER\"><Data use=\"pnesystems\" name=\"companyname\" /><Data use=\"218.147.101.161:8085\" name=\"server\" /></Install><Install size=\"2\" name=\"WEATHER\"><Data use=\"10000\" name=\"regionCode\" /><Data use=\"localhost\" name=\"server\" /></Install><Install size=\"5\" name=\"VENTILATION\"><Data use=\"Yes\" name=\"normal\">일반</Data><Data use=\"Yes\" name=\"sleep\">취침</Data><Data use=\"Yes\" name=\"pleasant\">전열</Data><Data use=\"Yes\" name=\"auto\">자동</Data><Data use=\"no\" name=\"powersave\" /></Install><Install size=\"2\" name=\"EXTERNALSERVER\"><Data use=\"8880\" name=\"port\" /><Data use=\"124.111.208.72\" name=\"server\" /></Install><Install size=\"1\" name=\"DEBUGOPTION\"><Data use=\"0\" name=\"debug\" /></Install><Install size=\"2\" name=\"CCTV_RES\"><Data name=\"width\">352</Data><Data name=\"height\">240</Data></Install></install_info_configure>  </ControlResponse></HNML>";
//        String string = "<ControlResponse TransID=\"WP20191231143033917\">\n" +
//                "    <FunctionCategory>Control</FunctionCategory>\n" +
//                "    <Result>0000</Result>\n" +
//                "    <OutputList size=\"2\">\n" +
//                "      <Output size=\"9\" name=\"HomeInfo\">\n" +
//                "        <Data name=\"HomeID\">000001011402</Data>\n" +
//                "        <Data name=\"StartTime\">2019-12-01</Data>\n" +
//                "        <Data name=\"EndTime\">2019-12-31</Data>\n" +
//                "        <Data name=\"CategoryType\">Electricity</Data>\n" +
//                "        <Data name=\"QueryType\">Day</Data>\n" +
//                "        <Data name=\"TotalAverage\">0.69</Data>\n" +
//                "        <Data name=\"SameAverage\">2.92</Data>\n" +
//                "        <Data name=\"MyAverage\">2.92</Data>\n" +
//                "        <Data name=\"TodayUsed\">87.70</Data>\n" +
//                "      </Output>\n" +
//                "      <Outputs size=\"30\" name=\"Electricity\">\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-01T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-01T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">1.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-02T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-02T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-03T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-03T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-04T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-04T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">4.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-05T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-05T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">5.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-06T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-06T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-07T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-07T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.20</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-08T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-08T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">0.40</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-09T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-09T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.80</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-10T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-10T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.70</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-11T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-11T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.20</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-12T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-12T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.50</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-13T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-13T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">0.90</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-14T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-14T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-15T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-15T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-16T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-16T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-17T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-17T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.50</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-18T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-18T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.60</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-19T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-19T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">0.70</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-20T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-20T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.50</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-21T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-21T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">7.40</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-22T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-22T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">4.10</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-23T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-23T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-24T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-24T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">1.00</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-25T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-25T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">1.80</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-26T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-26T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">7.20</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-27T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-27T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">5.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-28T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-28T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">1.90</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-29T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-29T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">2.70</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "        <Output size=\"4\">\n" +
//                "          <Data name=\"StartTime\">2019-12-30T00:00:00</Data>\n" +
//                "          <Data name=\"EndTime\">2019-12-30T23:59:59</Data>\n" +
//                "          <Data name=\"Amount\">3.30</Data>\n" +
//                "          <Data name=\"Unit\">KWh</Data>\n" +
//                "        </Output>\n" +
//                "      </Outputs>\n" +
//                "    </OutputList>\n" +
//                "  </ControlResponse>";

//        try {
//            List<Data> list = new ArrayList<>();
//            list.add(new Data("HomeID", "0"));
//            list.add(new Data("HomeID1", "1"));
//            list.add(new Data("HomeID2", "2"));
//            list.add(new Data("HomeID3", "3"));
//            list.add(new Data("HomeID4", "4"));
//            Input input = new Input("QueryInfo", "5", "0", list);
//            InputList inputList = new InputList(input, "1");
//            ControlRequest controlRequest = new ControlRequest("123", "Energy", "123", inputList);
//            HNML<ControlRequest> object = new HNML<>(controlRequest); // test data
//            XMLUtils<HNML> utils = new XMLUtils<>();
//            String data = utils.convertObjectToXML(object);
//            System.out.println("data--123: "+data);
////            System.out.println("data-convert-from-object: " + utils.convertXMLToObject(data, HNML.class).controlRequestObject.t);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        XMLUtils<HNMLLogin> utils = new XMLUtils<>();
        HNMLLogin object = utils.convertXMLToObject(s, HNMLLogin.class);
        System.out.println("TransID: " + object.getControlResponse().getTransID());
        System.out.println("FunctionID: " + object.getControlResponse().getFunctionID());
        System.out.println("FunctionCategory: " + object.getControlResponse().getFunctionCategory());
        System.out.println("Result: " + object.getControlResponse().getResult());
        System.out.println("OutputList size: " + object.getControlResponse().getOutputList().getSize());
        int size = Integer.parseInt(object.getControlResponse().getOutputList().getSize());

        /**
         * get data from OutputList*/
        for (int i = 0; i < size; i++) {
            System.out.println("pos: " + i);
            System.out.println("Output size at +" + i + "+: " + object.getControlResponse().getOutputList().getListOutput().get(i).getSize());
            int sizeData = object.getControlResponse().getOutputList().getListOutput().get(i).getData().size();
            for (int h = 0; h < sizeData; h++) {
                System.out.println("data value at" + h + ": " + object.getControlResponse().getOutputList().getListOutput().get(i).getData().get(h).getName() +
                        "-" + object.getControlResponse().getOutputList().getListOutput().get(i).getData().get(h).getValue());
            }
        }
        /**
         * get data from install_info_configure
         * */

        System.out.println("---------DATA INSTALL--------");
        int sizeInstallinfo = object.getControlResponse().getInstall_info_configure().getListInstall().size();
        System.out.println("sitename: " + object.getControlResponse().getInstall_info_configure().getSitename());
        System.out.println("version: " + object.getControlResponse().getInstall_info_configure().getVersion());
        for (int i = 0; i < sizeInstallinfo; i++) {
            int sizeListInstall = Integer.parseInt(object.getControlResponse().getInstall_info_configure().getListInstall().get(i).getSize());
            System.out.println("name: " + object.getControlResponse().getInstall_info_configure().getListInstall().get(i).getName());
            for (int j = 0; j < sizeListInstall; j++) {
                System.out.println("data value at" + j + ": " + object.getControlResponse().getInstall_info_configure().getListInstall()
                        .get(i).getListDataDevice().get(j).getUse() +
                        "-" + object.getControlResponse().getInstall_info_configure().getListInstall().get(i).getListDataDevice().get(j).getName());
            }
        }

        /**
         * get data from device_configure
         * */
        System.out.println("---------DATA DEVICE--------");
        System.out.println("sitename: " + object.getControlResponse().getDevice_configure().getSitename());
        System.out.println("version: " + object.getControlResponse().getDevice_configure().getVersion());
        int sizeListDeviceTotal = object.getControlResponse().getDevice_configure().getListDevice().size();
        for (int i = 0; i < sizeListDeviceTotal; i++) {
            int sizeListDevice = Integer.parseInt(object.getControlResponse().getDevice_configure().getListDevice().get(i).getSize());
            System.out.println("name: " + object.getControlResponse().getDevice_configure().getListDevice().get(i).getName());
            for (int j = 0; j < sizeListDevice; j++) {
                System.out.println("data value at" + j + ": " + object.getControlResponse().getDevice_configure().getListDevice().get(i).getList().get(j).getName() +
                        "-" + object.getControlResponse().getDevice_configure().getListDevice().get(i).getList().get(j).getValue());
            }
        }

        try {
            XMLUtils<HNMLLoginRequest> xmlUtils = new XMLUtils<>();
            String transID = "WP20160101000008070";
            String functionID = "11070001";
            String functionCategory = "Control";
            String sizeInputList = "1";
            String nameInput = "LoginInfo";
            String sizeInput = "10";
            List<Data> list = new ArrayList<>();
            list.add(new Data("IPAddress", "10.9.1.3"));
            list.add(new Data("PhysicalAddress", "F0:15:A0:01:38:6E"));
            list.add(new Data("Port", "8800"));
            list.add(new Data("SoftwareType", "HN"));
            list.add(new Data("SoftwareVersion", "1.0"));
            list.add(new Data("UpgradeStatus", "SUCCESS"));
            list.add(new Data("ModelName", "UHN-1010VE"));
            list.add(new Data("UserPassword", ""));
            list.add(new Data("LobbyPassword", "0000"));
            list.add(new Data("RemotePassword", ""));
            Input input = new Input(nameInput, sizeInput, list);
            InputList inputList = new InputList(input, sizeInputList);
            ControlRequest controlRequest = new ControlRequest(transID, functionID, functionCategory, inputList);
            HNMLLoginRequest hnmlLoginRequest = new HNMLLoginRequest(controlRequest);
            xmlUtils.convertObjectToXML(hnmlLoginRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int sizeListTotal = object.ControlResponse.install_info_configure.listInstall.size();
//        for (int i = 0; i < sizeListTotal; i++) {
//            int sizeListInstall = Integer.parseInt(object.ControlResponse.install_info_configure.listInstall.get(i).size);
//            int sizeListDataDevice = Integer.parseInt(object.ControlResponse.install_info_configure.listInstall.get(i).size);
//            System.out.println("value name " + i + ":" + object.ControlResponse.install_info_configure.listInstall.get(i).name);
//            System.out.println("size at " + i + " : " + sizeListDataDevice);
//            for (int j = 0; j < sizeListInstall; j++) {
//
////                for (int h = 0; h < sizeListDataDevice; h++) {
////                    System.out.println("value " + h + ":" + object.ControlResponse.install_info_configure.listInstall.get(j).listDataDevice.get(h).name);
////                }
//
//            }
//        }
//        System.out.println("data-size:"+sizeListInstall);
    }
}
