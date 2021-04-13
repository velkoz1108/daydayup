package com.eden;

import org.apache.thrift.TConfiguration;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 7911;
    public static final int TIMEOUT = 30000;

    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(new TConfiguration(), SERVER_IP, SERVER_PORT, TIMEOUT);

            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            transport.open();
            ;
            String result = client.sayHello(userName);
            System.out.println("Thrify client result = " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e1) {
            e1.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }


    }

    public static void main(String[] args) {
        ThriftClient client = new ThriftClient();
        client.startClient("Linda");
    }
}
