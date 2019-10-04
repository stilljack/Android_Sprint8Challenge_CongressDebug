package com.lambdaschool.congressdata.congressdataapiaccess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class NetworkAdapter {

    private static final int TIMEOUT = 3000;
    private static final String API_KEY = "glCbNQgEiisCdhTwucffBYZfvBXiHCiZ18S2pEFL";

    public static String httpGetRequest(final String urlString) {
        final String[] result = new String[1];
        Thread downloadThread = new HandlerThread("UIHandler"){
            @Override
            public void run(){
                InputStream stream = null;
                HttpsURLConnection connection = null;
                try {
                    URL url = new URL(urlString);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestProperty("x-api-key", API_KEY);
                    connection.setReadTimeout(TIMEOUT);
                    connection.setConnectTimeout(TIMEOUT);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        result[0] = Integer.toString(responseCode);
                        throw new IOException("HTTP error code: " + responseCode);
                    }

                    stream = connection.getInputStream();
                    if (stream != null) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuilder sb = new StringBuilder();

                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line).append('\n');
                        }
                        result[0] = sb.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Close Stream and disconnect HTTPS connection.
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        };
        downloadThread.start();

        try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    public static Bitmap getBitmapFromURL(final String urlString) {
        final Bitmap[] result = new Bitmap[1];
        Thread downloadThread = new HandlerThread("UIHandler"){
            @Override
            public void run(){
                HttpsURLConnection connection = null;
                try {

                    URL url = new URL(urlString);
                    connection = (HttpsURLConnection) url
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    result[0] = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        };
        downloadThread.start();

        try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }
}
