package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler {

    static Socket socket;
    static BufferedReader in;
    static PrintWriter out;

    static {
        try {
            socket = new Socket("localhost", 1234);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
		
    }

    public static Object PosaljiPaketIPrimiOdgovor(String p) throws IOException {
        out.println(p);
        return in.readLine();
    }

    public static void CloseAll() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}
