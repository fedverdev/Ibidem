package com.github.fedverdev.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Sender {
    private final InetAddress receiverAddress;
    private final int receiverPort;
    private final DatagramSocket socket;

    public Sender(DatagramSocket socket, String receiverIP, int receiverPort) throws UnknownHostException {
        this.receiverAddress = InetAddress.getByName(receiverIP);
        this.receiverPort = receiverPort;
        this.socket = socket;
    }

    public void sendFrom(byte[] payload) throws IOException {
        DatagramPacket packet = new DatagramPacket(payload, payload.length, receiverAddress, receiverPort);
        socket.send(packet);
    }
}
