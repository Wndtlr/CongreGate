package com.congregate.utils;
import com.fazecast.jSerialComm.*;

public class SerialReader implements Runnable {
    private volatile boolean running;
    private SerialPort serialPort;

    public SerialReader() {
        running = true;
        serialPort = SerialPort.getCommPort("/dev/ttyUSB0"); // Adjust port as needed
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
    }

    @Override
    public void run() {
        try {
            while (running) {
                if (serialPort.bytesAvailable() > 0) {
                    byte[] buffer = new byte[serialPort.bytesAvailable()];
                    serialPort.readBytes(buffer, buffer.length);
                    String data = new String(buffer);
                    System.out.println("Received from serial port: " + data.trim());
                    // Pass data to Python script (e.g., through stdin or command-line arguments)
                    // You can use ProcessBuilder to run the Python script here
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopReading() {
        running = false;
        serialPort.closePort();
    }
}