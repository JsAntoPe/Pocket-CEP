package com.siddhiApi.webhook;

import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Webhook{
    private final String url;
    private final String method;
    private final String body;

    static AsyncHttpClient asyncClient = Dsl.asyncHttpClient();

    Logger logger = LoggerFactory.getLogger(Webhook.class);

    public Webhook(String url, String method, String body) throws MalformedURLException {
        this.url = url;
        this.method = method;
        this.body = body;

        //JSONObject jsonToSend = new JSONObject(body);
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
        /*try {
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
        }*/

        OutputStream os = new ByteArrayOutputStream();
        byte[] input = null;
        try {
            input = this.body.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert input != null;

        Request request = new RequestBuilder()
                .setUrl(this.url)
                .setMethod(this.method)
                .setBody(this.body)
                //.setBody(input)
                .setHeader("Content-Type", "application/json")
                //.setCharset()//
                .build();

        asyncClient.executeRequest(request, new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) throws Exception {
                logger.info("Inside execute request.");
                return null;
            }
        });

        logger.info("URL: " + this.url);
        logger.info("Method: " + this.method);
        logger.info("Body: " + this.body);

        //thread.start();
    }
}
