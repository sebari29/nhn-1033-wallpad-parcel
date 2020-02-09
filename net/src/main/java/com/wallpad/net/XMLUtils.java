package com.wallpad.net;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XMLUtils<T> {
    public String convertObjectToXML(T object) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            xmlMapper.writeValue(byteArrayOutputStream, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("data xml: " + byteArrayOutputStream.toString());
        return byteArrayOutputStream.toString();
    }

    public T convertXMLToObject(String string, Class<T> clazz) throws IOException {
        final JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(module);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        T object = null;
        try {
            object = objectMapper.readValue(string, clazz);
            /**
             * Sample for com.wallpad.net.simple.HNML
             * NOT IS Sample for com.wallpad.net.simple.request.HNML
             * please NOTE!
             * */
//            HNML bean = objectMapper.readValue(string, HNML.class);
//            System.out.println(bean.controlResponseObject.transID);
//            System.out.println(bean.controlResponseObject.functionID);
//            System.out.println(bean.controlResponseObject.functionCategory);
//            System.out.println("size:" + bean.controlResponseObject.outputList.outputObject._size);
//            System.out.println("name:" + bean.controlResponseObject.outputList.outputObject._name);
//            int size = Integer.parseInt(bean.controlResponseObject.outputList.outputObject._size);
//            for (int i = 0; i < size; i++) {
//                System.out.println("value" + i + ":" + bean.controlResponseObject.outputList.outputObject.data.get(i).name + "-" + bean.controlResponseObject.outputList.outputObject.data.get(i).valueData);
//            }
//            int sizeList = Integer.parseInt(bean.controlResponseObject.outputList.otputsObject._size);
//            for (int i = 0; i < sizeList; i++) {
//                int sizeOutPutItem = Integer.parseInt(bean.controlResponseObject.outputList.otputsObject.Output.get(i)._size);
//                System.out.println(sizeOutPutItem);
//                for (int j = 0; j < sizeOutPutItem; j++) {
//                    System.out.println("value output" + j + ":" + bean.controlResponseObject.outputList.otputsObject.Output.get(i).data.get(j).name + "-" + bean.controlResponseObject.outputList.otputsObject.Output.get(i).data.get(j).valueData);
//                }
//            }

        } catch (IOException e) {
           throw e;
        }
        return object;
    }
}
