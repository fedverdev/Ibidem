package com.github.fedverdev;

import com.github.fedverdev.audio.AudioCapture;
import com.github.fedverdev.audio.AudioPlayback;
import com.github.fedverdev.network.Receiver;
import com.github.fedverdev.network.Sender;

public class IbedemApp {
    public static void main(String[] args) {
        try {
            AudioCapture audioCapture = new AudioCapture();
            AudioPlayback audioPlayback = new AudioPlayback();

            String rcvIP = args[0];
            int rcvPort = Integer.parseInt(args[1]);
            int myPort = Integer.parseInt(args[2]);

            Receiver receiver = new Receiver(myPort);
            Sender sender = new Sender(receiver.getSocket(), rcvIP, rcvPort);

            byte[] dataToSend = new byte[1024];

            System.out.println("Your port is " + receiver.getReceiverPort());

            while (true) {
                audioCapture.captureTo(dataToSend);
                sender.sendFrom(dataToSend);
                audioPlayback.playFrom(receiver.getData());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }
}