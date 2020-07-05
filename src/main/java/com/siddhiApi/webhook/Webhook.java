package com.siddhiApi.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Webhook{
    private final URL url;
    private final String method;
    private final String body;



    Logger logger = LoggerFactory.getLogger(Webhook.class);

    public Webhook(String url, String method, String body) throws MalformedURLException {
        this.url = new URL(url);
        this.method = method;
        this.body = body;
        /*Thread thread = new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
                connection.setRequestMethod(this.method);
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);
                try(OutputStream os = connection.getOutputStream()) {
                    byte[] input = this.body.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                logger.info("Connection Request method: " +connection.getRequestMethod());
                logger.info("Connection Content type: " +connection.getRequestProperty("Content type"));
                logger.info("Connection Do output: " +connection.getDoOutput());
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
        try {
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod(this.method);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = this.body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            logger.info("Connection Request method: " +connection.getRequestMethod());
            logger.info("Connection Content type: " +connection.getRequestProperty("Content-Type"));
            logger.info("Connection Do output: " +connection.getDoOutput());
            connection.connect();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                logger.info(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("URL: " + this.url);
        logger.info("Method: " + this.method);
        logger.info("Body: " + this.body);

        //thread.start();
    }
}
