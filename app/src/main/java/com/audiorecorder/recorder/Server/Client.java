
package com.audiorecorder.recorder.Server;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, String> {

    private static String serverAdress;
    private static int serverPort = 8080;
    private static  String response = "";
    private static TextView textResponse;

    Client(String addr, int port, String responses) {
        serverAdress = "\\\\dt-srv-file1.ehb.local\\Studentenhomes\\loic.nigra";
        serverPort = port;
        response = responses;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(serverAdress, serverPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();


            // notice: inputStream.read() will block if no data return

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {

            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}

