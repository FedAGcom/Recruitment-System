package com.fedag.recruitmentSystem.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author koilng
 * @created 20/06/2022 - 12:42
 */
public class CompilerApi {

  String clientId = "f101a6362a8b2d1dc4f7871d49d73e52"; //Replace with your client ID
  String clientSecret = "2d9a4145a769e39a67c8d56df05e9dccdbeedcdccd889a3f2d6b155d61de9b7a"; //Replace with your client Secret
  String script;
  String language = "java";
  String versionIndex = "3";

  public CompilerApi(String script) {
    this.script = script;
  }

  public String execute() {
    StringBuilder sb = new StringBuilder();
    try {
      URL url = new URL("https://api.jdoodle.com/v1/execute");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");

      String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
          "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(input.getBytes());
      outputStream.flush();

      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("Please check your inputs : HTTP error code : " + connection.getResponseCode());
      }

      BufferedReader bufferedReader;
      bufferedReader = new BufferedReader(new InputStreamReader(
          (connection.getInputStream())));

      String output;
      while ((output = bufferedReader.readLine()) != null) {
        sb.append(output).append("\n");
      }

      connection.disconnect();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
}
