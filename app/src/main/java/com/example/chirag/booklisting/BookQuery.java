package com.example.chirag.booklisting;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

public final class BookQuery {

    public static final String LOG_TAG = BookQuery.class.getName();

    public BookQuery() {
    }

    public static List<BookInfo> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making HTTP request.", e);
        }

        List<BookInfo> bookInfoList = extractBookInfo(jsonResponse);
        return bookInfoList;
    }

    private static URL createUrl(String findUrl) {
        URL url = null;

        try {
            url = new URL(findUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL.", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retriving the book info JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Not able to read anything from InputStream", e);
        }
        return output.toString();
    }

    public static List<BookInfo> extractBookInfo(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        List<BookInfo> bookInfos = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(bookJSON);
            JSONArray itemArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < itemArray.length(); i++) {
                JSONObject elements = itemArray.getJSONObject(i);
//                JSONObject priceElements = elements.getJSONObject("saleInfo");
                JSONObject volumeInfo = elements.getJSONObject("volumeInfo");

                String bookName = volumeInfo.getString("title");
                String bookAuthor = volumeInfo.getString("authors");
                String bookPublisher = volumeInfo.getString("publisher");

//                JSONObject price = priceElements.getJSONObject("listPrice");


                bookInfos.add(new BookInfo(bookName, bookAuthor, bookPublisher, 4.0, 25));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the data JSON results", e);
        }
        return bookInfos;
    }
}
