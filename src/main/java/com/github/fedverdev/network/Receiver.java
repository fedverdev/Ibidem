package com.github.fedverdev.network;

import java.io.IOException;
import java.net.*;
import java.util.Random;

public class Receiver {
    private final DatagramSocket socket;
    private final int receiverPort;
    private final InetAddress receiverAddress;

    public Receiver() throws SocketException, UnknownHostException {
        Random random = new Random();
        this.receiverAddress = InetAddress.getByName("0.0.0.0");
        this.receiverPort = random.nextInt(NetworkConfig.PORT_MAX - NetworkConfig.PORT_MIN + 1) + NetworkConfig.PORT_MIN;
        this.socket = new DatagramSocket(receiverPort, receiverAddress);
    }

    public Receiver(int port) throws SocketException, UnknownHostException {
        Random random = new Random();
        this.receiverAddress = InetAddress.getByName("0.0.0.0");
        this.receiverPort = port;
        this.socket = new DatagramSocket(receiverPort, receiverAddress);
    }

    public int getReceiverPort() {
        return receiverPort;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public byte[] getData() throws IOException {
        DatagramPacket packet = new DatagramPacket(new byte[1024],  1024);
        socket.receive(packet);
        return packet.getData();
    }
}
