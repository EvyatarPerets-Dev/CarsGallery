package perets.app.thecargalleryv2.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Input Output class -
 * Helping with a global methods (shortcut).
 */
public class IO {


    public static String getUrlHttp(String address)throws IOException {
        URL url = new URL(address);
        HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = httpsURLConnection.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
       return sb.toString();
    }
    public static String getUrlHttps(String address)throws IOException {
        URL url = new URL(address);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = httpsURLConnection.getInputStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
