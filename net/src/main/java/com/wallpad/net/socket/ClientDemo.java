package com.wallpad.net.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {
        // Địa chỉ máy chủ.
        final String serverHost = "124.111.208.72";

        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;

        try {
            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            // trên máy 'localhost' cổng 7777.
            socketOfClient = new Socket(serverHost, 8800);

            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }

        try {
            // Ghi dữ liệu vào luồng đầu ra của Socket tại Client.
            os.write("<HNML>\n" +
                    "  <ControlRequest TransID=\"WP20191227182525445\">\n" +
                    "    <FunctionID>11070001</FunctionID>\n" +
                    "    <FunctionCategory>Control</FunctionCategory>\n" +
                    "    <InputList size=\"1\">\n" +
                    "      <Input size=\"10\" name=\"LoginInfo\">\n" +
                    "        <Data name=\"IPAddress\">10.10.1.1</Data>\n" +
                    "        <Data name=\"PhysicalAddress\" />\n" +
                    "        <Data name=\"Port\">8800</Data>\n" +
                    "        <Data name=\"SoftwareType\">HN</Data>\n" +
                    "        <Data name=\"SoftwareVersion\">1.0</Data>\n" +
                    "        <Data name=\"UpgradeStatus\">SUCCESS</Data>\n" +
                    "        <Data name=\"ModelName\">NHN-1031</Data>\n" +
                    "        <Data name=\"UserPassword\" />\n" +
                    "        <Data name=\"LobbyPassword\">0000</Data>\n" +
                    "        <Data name=\"RemotePassword\" />\n" +
                    "      </Input>\n" +
                    "    </InputList>\n" +
                    "  </ControlRequest>\n" +
                    "</HNML>\n" );
            os.newLine(); // kết thúc dòng
            os.flush();  // đẩy dữ liệu đi.


            // Đọc dữ liệu trả lời từ phía server
            // Bằng cách đọc luồng đầu vào của Socket tại Client.
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.indexOf("OK") != -1) {
                    break;
                }
            }

            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

}