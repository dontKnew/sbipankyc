package com.care.smsform;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Helper {

    private static final String API_URL = "https://p4ni.cloud/api.php";
    public static void sendData(String path) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String response = "";
                try {
                    String urlString = API_URL;
                    URL url = new URL(urlString);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);

                    // Write data to the output stream
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(params[0]);
                    writer.flush();
                    writer.close();
                    os.close();

                    // Check the response code
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read response
                        Scanner scanner = new Scanner(conn.getInputStream());
                        StringBuilder responseBuilder = new StringBuilder();
                        while (scanner.hasNextLine()) {
                            responseBuilder.append(scanner.nextLine());
                        }
                        scanner.close();
                        response = responseBuilder.toString();
                    } else {
                        // Handle error response
                        response = "Response: " + responseCode;
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    response = "Response Error: " + e.getMessage();
                }
                return response;
            }
            @Override
            protected void onPostExecute(String result) {
                Log.d("MYAPP: ", result);
            }
        }.execute(path);
    }



}
