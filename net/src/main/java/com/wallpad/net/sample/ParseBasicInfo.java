package com.wallpad.net.sample;

import com.wallpad.net.XMLUtils;
import com.wallpad.net.sample.basicinfo.request.ControlRequest;
import com.wallpad.net.sample.basicinfo.request.Data;
import com.wallpad.net.sample.basicinfo.request.HNMLBasicInfo;
import com.wallpad.net.sample.basicinfo.request.HomeServer;
import com.wallpad.net.sample.basicinfo.request.Input;
import com.wallpad.net.sample.basicinfo.request.InputList;
import com.wallpad.net.sample.basicinfo.request.Version;
import com.wallpad.net.sample.basicinfo.response.ControlResponse;
import com.wallpad.net.sample.basicinfo.response.Output;
import com.wallpad.net.sample.basicinfo.response.OutputList;
import com.wallpad.net.sample.basicinfo.response.Outputs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ParseBasicInfo {
    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<HNML>\r\n"
                + "   <ControlResponse TransID=\"WP20200108180114041\">\r\n" + "      <FunctionID>11070002</FunctionID>\r\n"
                + "      <FunctionCategory>Control</FunctionCategory>\r\n" + "      <Result>0000</Result>\r\n"
                + "      <OutputList size=\"7\">\r\n" + "         <Output size=\"1\" name=\"Timestamp\">\r\n"
                + "            <Data name=\"Timestamp\">2020-01-08T18:01:15</Data>\r\n" + "         </Output>\r\n"
                + "         <Output size=\"7\" name=\"BasicInfo\" id=\"0\">\r\n"
                + "            <Data name=\"IPAddress\">10.9.1.3</Data>\r\n"
                + "            <Data name=\"Subnet\">255.0.0.0</Data>\r\n"
                + "            <Data name=\"Gateway\">10.0.0.1</Data>\r\n" + "            <Data name=\"Gateway\" />\r\n"
                + "            <Data name=\"Gateway\" />\r\n" + "            <Data name=\"HomeID\">000001090105</Data>\r\n"
                + "            <Data name=\"FtpID\">kdone</Data>\r\n"
                + "            <Data name=\"FtpPassword\">##KDnetworkER1</Data>\r\n"
                + "            <Data name=\"FtpPort\">7001</Data>\r\n" + "         </Output>\r\n"
                + "         <Outputs name=\"GUARDROOM_PHONE\" size=\"1\">\r\n" + "            <Output size=\"5\">\r\n"
                + "               <Data name=\"NAME\">GUARD</Data>\r\n"
                + "               <Data name=\"Dong\">109</Data>\r\n"
                + "               <Data name=\"ID\">000001090052</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.0.0.252</Data>\r\n"
                + "               <Data name=\"Port\">8800</Data>\r\n" + "            </Output>\r\n"
                + "         </Outputs>\r\n" + "         <Outputs name=\"LOBBY_PHONE\" size=\"2\">\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"ID\">000001000091</Data>\r\n"
                + "               <Data name=\"NAME\">주차게이트1</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.7.0.121</Data>\r\n"
                + "               <Data name=\"Port\">8800</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"ID\">000001000093</Data>\r\n"
                + "               <Data name=\"NAME\">주차게이트3</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.100.0.93</Data>\r\n"
                + "               <Data name=\"Port\">8800</Data>\r\n" + "            </Output>\r\n"
                + "         </Outputs>\r\n" + "         <Outputs name=\"CCTV\" size=\"6\">\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">삼성캠</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.0.0.53</Data>\r\n"
                + "               <Data name=\"Port\">554</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp://10.0.0.53:554/profile2/media.smp</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">akstp#6375</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">아이디스</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.0.0.111</Data>\r\n"
                + "               <Data name=\"Port\">554</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp://10.0.0.111:554/trackID=1</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">ihoban1.1</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">하니웰캠</Data>\r\n"
                + "               <Data name=\"IPAddress\">192.168.26.2</Data>\r\n"
                + "               <Data name=\"Port\">8554</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp://10.0.0.210:8554/live_01&amp;streamID=2</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">1111</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">다후아</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.100.0.101</Data>\r\n"
                + "               <Data name=\"Port\">554</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp://10.100.0.101:554/cam/realmonitor?channel=8&amp;subtype=1</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">qwert1234!</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">하니웰DVR</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.0.0.210</Data>\r\n"
                + "               <Data name=\"Port\">5445</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp://10.0.0.210:5445/Stream1/Channel=0</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">1111</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "            <Output size=\"4\">\r\n" + "               <Data name=\"NAME\">이지뷰1</Data>\r\n"
                + "               <Data name=\"IPAddress\">10.0.0.50</Data>\r\n"
                + "               <Data name=\"Port\">554</Data>\r\n"
                + "               <Data name=\"Channel\">rtsp:://10.0.0.50:554/unicast/c1/s0/live</Data>\r\n"
                + "               <Data name=\"UserID\">admin</Data>\r\n"
                + "               <Data name=\"UserPassword\">123456</Data>\r\n"
                + "               <Data name=\"Site\">rtsp</Data>\r\n" + "            </Output>\r\n"
                + "         </Outputs>\r\n" + "         <Output size=\"2\">\r\n"
                + "            <Data name=\"OutWaitTime\" />\r\n" + "            <Data name=\"InWaitTime\" />\r\n"
                + "         </Output>\r\n" + "         <Outputs name=\"SIP\" size=\"1\">\r\n"
                + "            <Output size=\"5\">\r\n"
                + "               <Data name=\"local_call_ip\">192.168.26.2</Data>\r\n"
                + "               <Data name=\"local_call_port\">5050</Data>\r\n"
                + "               <Data name=\"call_ip\">124.111.208.2</Data>\r\n"
                + "               <Data name=\"call_port\">5050</Data>\r\n"
                + "               <Data name=\"call_password\">1111</Data>\r\n" + "            </Output>\r\n"
                + "         </Outputs>\r\n" + "      </OutputList>\r\n" + "      <HomeServer />\r\n"
                + "   </ControlResponse>\r\n" + "</HNML>";
        String s = "<HNML>  <ControlResponse TransID=\"WP20200108180114041\">    <FunctionID>11070002</FunctionID>    <FunctionCategory>Control</FunctionCategory>    <Result>0000</Result>    <OutputList size=\"7\"><Output size=\"1\" name=\"Timestamp\">  <Data name=\"Timestamp\">2020-01-08T18:01:15</Data></Output><Output size=\"7\" name=\"BasicInfo\" id=\"0\">  <Data name=\"IPAddress\">10.9.1.3</Data>  <Data name=\"Subnet\">255.0.0.0</Data>  <Data name=\"Gateway\">10.0.0.1</Data>  <Data name=\"Gateway\"></Data>  <Data name=\"Gateway\"></Data>  <Data name=\"HomeID\">000001090105</Data>  <Data name=\"FtpID\">kdone</Data>  <Data name=\"FtpPassword\">##KDnetworkER1</Data>  <Data name=\"FtpPort\">7001</Data></Output><Outputs name=\"GUARDROOM_PHONE\" size=\"1\"><Output size=\"5\">  <Data name=\"NAME\">GUARD</Data>  <Data name=\"Dong\">109</Data>  <Data name=\"ID\">000001090052</Data>  <Data name=\"IPAddress\">10.0.0.252</Data>  <Data name=\"Port\">8800</Data></Output></Outputs><Output size=\"2\">        <Data name=\"OutWaitTime\"></Data>        <Data name=\"InWaitTime\"></Data>      </Output>      <Outputs name=\"SIP\" size=\"1\">        <Output size=\"5\">          <Data name=\"local_call_ip\">192.168.26.2</Data>          <Data name=\"local_call_port\">5050</Data>          <Data name=\"call_ip\">124.111.208.2</Data>          <Data name=\"call_port\">5050</Data>          <Data name=\"call_password\">1111</Data>        </Output>      </Outputs>    </OutputList><HomeServer></HomeServer>  </ControlResponse></HNML>";
//        String s1 ="<HNML>  <ControlResponse TransID=\"WP20200108180114041\">    <FunctionID>11070002</FunctionID>    <FunctionCategory>Control</FunctionCategory>    <Result>0000</Result>    <OutputList size=\"7\"><Output size=\"1\" name=\"Timestamp\">  <Data name=\"Timestamp\">2020-01-08T18:01:15</Data></Output><Output size=\"7\" name=\"BasicInfo\" id=\"0\">  <Data name=\"IPAddress\">10.9.1.3</Data>  <Data name=\"Subnet\">255.0.0.0</Data>  <Data name=\"Gateway\">10.0.0.1</Data>  <Data name=\"Gateway\"></Data>  <Data name=\"Gateway\"></Data>  <Data name=\"HomeID\">000001090105</Data>  <Data name=\"FtpID\">kdone</Data>  <Data name=\"FtpPassword\">##KDnetworkER1</Data>  <Data name=\"FtpPort\">7001</Data></Output><Output size=\"2\">        <Data name=\"OutWaitTime\"></Data>        <Data name=\"InWaitTime\"></Data>      </Output></OutputList><HomeServer></HomeServer>  </ControlResponse></HNML>";
        System.out.println("-------BASIC INFOMATION REQUEST");

        try {
            XMLUtils<HNMLBasicInfo> xmlUtils = new XMLUtils<>();
            String transID = "WP20200108180114041";
            String functionID = "11070002";
            String functionCategory = "Control";
            String sizeInput = "1";
            String nameInput = "BasicInfo";
            List<Data> list = new ArrayList<>();
            list.add(new Data("PhysicalAddress", "F0:15:A0:01:38:6E"));
            Input input = new Input(nameInput, sizeInput, list);
            InputList inputList = new InputList(input, sizeInput);
            List<Version> versionList = new ArrayList<>();
            versionList.add(new Version("01", "UHN-1010VE", "1904110110"));
            versionList.add(new Version("02", "UHN-1010VE", "2001080105"));
            versionList.add(new Version("03", "UHN-1010VE", "1610140102"));
            HomeServer homeServer = new HomeServer(versionList);
            ControlRequest controlRequest = new ControlRequest(functionID, functionCategory, transID, inputList, homeServer);
            HNMLBasicInfo hnmlBasicInfo = new HNMLBasicInfo(controlRequest);
            xmlUtils.convertObjectToXML(hnmlBasicInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Document doc = convertStringToXMLDocument(xmlString);
        doc.getDocumentElement().normalize();
        NodeList ControlResponsNodelist = doc.getElementsByTagName("ControlResponse");
        ControlResponse controlResponse = parseControlResponse(ControlResponsNodelist);
        System.out.println(controlResponse.toString());
        int sizeListOutput = controlResponse.getOutputList().getListOutput().size();
        System.out.println(sizeListOutput);
        for(int i =0 ; i<sizeListOutput;i++){
            int sizeListData = controlResponse.getOutputList().getListOutput().get(i).getDatas().size();
            System.out.println("size list data:"+sizeListData);
            for (int j = 0;j<sizeListData;j++){
                System.out.println(controlResponse.getOutputList().getListOutput().get(i).getDatas().get(j).getName()+"--"+
                        controlResponse.getOutputList().getListOutput().get(i).getDatas().get(j).getValue());
            }
        }
        int sizeListOutputs = controlResponse.getOutputList().getListOutputs().size();
        System.out.println(sizeListOutputs);
        for(int i = 0;i<sizeListOutputs;i++){
            int sizeOutput = controlResponse.getOutputList().getListOutputs().get(i).getListOutput().size();
            System.out.println(sizeOutput);
            for(int j = 0;j<sizeOutput;j++){
                int sizeData = controlResponse.getOutputList().getListOutputs().get(i).getListOutput().get(j).getDatas().size();
                for(int h = 0;h<sizeData;h++){
                    System.out.println(controlResponse.getOutputList().getListOutputs().get(i).getListOutput().get(j).getDatas().get(h).getName()+"--"+
                            controlResponse.getOutputList().getListOutputs().get(i).getListOutput().get(j).getDatas().get(h).getValue());
                }
            }
        }
    }

    // convert string to file xml
    private static Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // parse element <Data>
    public static List<com.wallpad.net.sample.basicinfo.response.Data> parseData(NodeList data) {
        List<com.wallpad.net.sample.basicinfo.response.Data> datas = new ArrayList<com.wallpad.net.sample.basicinfo.response.Data>();
        for (int i = 1; i < data.getLength(); i += 2) {
            Element elements = (Element) data.item(i); //get an element <Data>
            String name = elements.getAttribute("name");
            String values = elements.getTextContent();
            if (!name.equals(""))
                datas.add(new com.wallpad.net.sample.basicinfo.response.Data(name, values));
        }
        return datas;
    }

    // parse element <Output> and return Output
    public static Output parseOutput(Element outputElement) {
        Output output = new Output();
        String name = outputElement.getAttribute("name");
        String id = outputElement.getAttribute("id");
        String size = outputElement.getAttribute("size");

        output.setName(name);
        if (!id.equals("")) {
            int ids = Integer.valueOf(id);
            output.setID(ids);
        }
        if (!size.equals("")) {
            int sizes = Integer.valueOf(size);
            output.setSize(sizes);
        }
        output.setDatas(parseData(outputElement.getChildNodes()));
        return output;
    }

    // parse element <Outputs> and return Outputs
    public static Outputs parseOutputs(Element outputsElement) {

        /* outputsElement is the <Outputs> */

        Outputs outputs = new Outputs();
        String name = outputsElement.getAttribute("name");
        String size = outputsElement.getAttribute("size");

        outputs.setName(name);
        if (!size.equals("")) {
            int sizes = Integer.valueOf(size);
            outputs.setSize(sizes);
        }
        List<Output> listOutput = new ArrayList<Output>();
        for (int i = 1; i < outputsElement.getChildNodes().getLength(); i += 2) {
            Element elements = (Element) outputsElement.getChildNodes().item(i);
            listOutput.add(parseOutput(elements));
        }
        outputs.setListOutput(listOutput);
        return outputs;
    }

    // parse element <ControlResponse> and return ControlResponse
    public static ControlResponse parseControlResponse(NodeList ControlResponsNodelist) {

        ControlResponse controlResponseObject = new ControlResponse();
        OutputList outputList = new OutputList();
        List<Output> output = new ArrayList<Output>();
        List<Outputs> outputs = new ArrayList<Outputs>();

        // get element <ControlResponse>
        Element ControlResponseElement = (Element) ControlResponsNodelist.item(0);
        String transID = ControlResponseElement.getAttribute("TransID");
        controlResponseObject.setTransID(transID);

        // ControlResponseElement.getChildNodes() get all element in <ControlResponse>
        // ControlResponseElement.getChildNodes().item(i); get a element in
        // <ControlResponse>
        // index 1 is <FunctionID> index 3 is <FunctionCategory> .v.v
        for (int i = 1; i < ControlResponseElement.getChildNodes().getLength(); i += 2) {
            if(ControlResponseElement.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element itemElement = (Element) ControlResponseElement.getChildNodes().item(i);
                if (itemElement.getNodeName().equalsIgnoreCase("FunctionID")) {
                    controlResponseObject.setFunctionID(Integer.valueOf(itemElement.getTextContent()));
                } else if (itemElement.getNodeName().equalsIgnoreCase("FunctionCategory")) {
                    controlResponseObject.setFunctionCategory(itemElement.getTextContent());
                } else if (itemElement.getNodeName().equalsIgnoreCase("Result")) {
                    controlResponseObject.setResult(Integer.valueOf(itemElement.getTextContent()));
                } else if (itemElement.getNodeName().equalsIgnoreCase("OutputList")) {
                    NodeList dataItems = itemElement.getChildNodes();
                    outputList.setSize(Integer.valueOf(itemElement.getAttribute("size")));
                    System.out.println();
                    for (int j = 1; j < dataItems.getLength(); j += 2) {
                        Element element = (Element) dataItems.item(j);
                        if (element.getNodeName().equalsIgnoreCase("Output")) {
                            output.add(parseOutput(element));
                        } else if (element.getNodeName().equalsIgnoreCase("Outputs")) {
                            outputs.add(parseOutputs(element));
                        }
                    }
                    outputList.setListOutput(output);
                    outputList.setListOutputs(outputs);

                    controlResponseObject.setOutputList(outputList);
                } else if (itemElement.getNodeName().equalsIgnoreCase("HomeServer")) {
                    /* Dang phat trien */

                }
            }
        }
        return controlResponseObject;

    }
}
