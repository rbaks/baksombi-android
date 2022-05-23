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
    public static final String AUTHORIZATION = "Authorization";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "https://baksombi.herokuapp.com/v1";
    private static HttpHelper INSTANCE = null;
    private OkHttpClient httpClient;
    private Gson gson;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build());
        if(token!=null){
            requestBuilder = requestBuilder.addHeader("Authorization", "Bearer "+token);
        }
        Request request = requestBuilder
                .build();
        Response response = httpClient.newCall(request).execute();

        return processResponse(response, classObject);
    }

    private Object processResponse(Response response, Class classObject) throws Exception
    {
        int code = response.code();

        if(code >= 300){
            System.out.println(response.body().string());
            throw new Exception(response.message());
        }
        if(classObject!=null){
            String str = response.body().string();
            return gson.fromJson(str, classObject);
        }
        return null;
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
        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build());
        if(token!=null){
            requestBuilder = requestBuilder.addHeader("Authorization", "Bearer "+token);
        }
        Request request = requestBuilder
                .post(bodyRequest)
                .build();
        Response response = httpClient.newCall(request).execute();
        return processResponse(response, classObject);
    }
}
