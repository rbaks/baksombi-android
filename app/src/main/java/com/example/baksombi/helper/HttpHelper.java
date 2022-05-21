package com.example.baksombi.helper;

import java.util.Map;
import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "https://07ad-154-126-100-144.ngrok.io";
    private static HttpHelper INSTANCE = null;
    private OkHttpClient httpClient;
    private Gson gson;

    private HttpHelper(){
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }
    /**
     * fonction pour initialiser une fois le helper
     * @return le singleton
     */
    public static HttpHelper getInstance(){
        if(INSTANCE == null){
            HttpHelper.INSTANCE = new HttpHelper();
        }
        return HttpHelper.INSTANCE;
    }

    public Object get(String urlConcat, Class classObject, Map<String, String> params) throws Exception{
        HttpUrl.Builder urlBuilder = getUrlBuilder(urlConcat, params);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();
        Response response = httpClient.newCall(request).execute();

        return processResponse(response, classObject);
    }

    private Object processResponse(Response response, Class classObject) throws Exception
    {
        int code = response.code();

        if(code >= 300){
            throw new Exception(response.message());
        }
        String str = response.body().string();
        return gson.fromJson(str, classObject);
    }

    private HttpUrl.Builder getUrlBuilder(String urlConcat, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(HttpHelper.URL + urlConcat).newBuilder();
        if(params != null){
            for(Map.Entry<String,String> entry: params.entrySet()){
                urlBuilder.addQueryParameter(entry.getKey(),entry.getValue());
            }
        }
        return urlBuilder;
    }

    public Object post(String urlConcat, Class classObject, Object body) throws Exception{
        RequestBody bodyRequest = RequestBody.create(gson.toJson(body), JSON);
        HttpUrl.Builder urlBuilder = getUrlBuilder(urlConcat,null);
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .post(bodyRequest)
                .build();
        Response response = httpClient.newCall(request).execute();
        return processResponse(response, classObject);
    }
}
