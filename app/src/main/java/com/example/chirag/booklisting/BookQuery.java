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

    private static final String LOG_TAG = BookQuery.class.getName();

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

    private static List<BookInfo> extractBookInfo(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        List<BookInfo> bookInfos = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(bookJSON);

            JSONArray itemsArray = null;
            if (jsonObject.has("items")) {
                itemsArray = jsonObject.getJSONArray("items");
            }

            if (itemsArray != null) {
                for (int i = 0; i < itemsArray.length(); i++) {

                    JSONObject elementsInItem = itemsArray.getJSONObject(i);

                    String bookTitle = bookInfo(elementsInItem, "title");
                    String bookPublisher = bookInfo(elementsInItem, "publisher");
                    String bookLink = bookInfo(elementsInItem, "infoLink");
                    String bookAuthors = bookInfo(elementsInItem, "authors");

                    String bookPrice = String.valueOf(bookData(elementsInItem, "saleInfo", "retailPrice", "amount"));
                    String url = bookData(elementsInItem, "volumeInfo", "imageLinks", "smallThumbnail");

                    double rating;
                    JSONObject ratingObject = null;
                    if (elementsInItem.has("volumeInfo")) {
                        ratingObject = elementsInItem.getJSONObject("volumeInfo");
                    }
                    if (ratingObject != null && ratingObject.has("averageRating")) {
                        rating = ratingObject.getDouble("averageRating");
                    } else {
                        rating = 0.0;
                    }

                    bookInfos.add(new BookInfo(bookTitle, bookAuthors, bookPublisher, bookLink, rating, bookPrice, url));
                }
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the data JSON results", e);
        }
        return bookInfos;
    }

    private static String bookInfo(JSONObject object, String key) throws JSONException {
        JSONObject jsonObject = null;
        StringBuilder arrayParameters = new StringBuilder();
        String value = "";
        JSONArray valueArray = null;
        if (object.has("volumeInfo")) {
            jsonObject = object.getJSONObject("volumeInfo");
        }
        if (jsonObject != null && key.equals("authors")) {
            if (jsonObject.has(key)) {
                valueArray = jsonObject.getJSONArray(key);
            }
            if (valueArray != null) {
                arrayParameters.append(valueArray.get(0));
                for (int i = 1; i < valueArray.length(); i++) {
                    arrayParameters.append(", ");
                    arrayParameters.append(valueArray.get(i));
                }
            }
            value = arrayParameters.toString();
            return value;
        }
        if (jsonObject != null) {
            value = jsonObject.getString(key);
            return value;
        }
        return value;
    }

    private static String bookData(JSONObject object1, String values, String key1, String key2) throws JSONException {
        JSONObject jsonObjectOut;
        JSONObject jsonObjectIn;
        String value = "Free";
        if (object1.has(values)) {
            jsonObjectOut = object1.getJSONObject(values);
            if (jsonObjectOut != null && jsonObjectOut.has(key1)) {
                jsonObjectIn = jsonObjectOut.getJSONObject(key1);
                if (jsonObjectIn != null && jsonObjectIn.has(key2)) {
                    value = jsonObjectIn.getString(key2);
                } else {
                    value = "Free";
                }
            }
        }
        return value;
    }
}
