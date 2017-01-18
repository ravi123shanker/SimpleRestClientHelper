package com.ravi.restclient;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ravishankeryadav on 8/27/2016.
 */
public class RestClientHelper {
    private static final String LOG_TAG = "RestClientHelper";
    public static String defaultBaseUrl = "";
    private static final Object lockObject = new Object();
    private static RestClientHelper restClientHelper;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private RestClientHelper() {
    }

    public static RestClientHelper getInstance() {
        if (restClientHelper == null)
            synchronized (lockObject) {
                if (restClientHelper == null)
                    restClientHelper = new RestClientHelper();
            }
        return restClientHelper;
    }

    private final Executor executor;

    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull final Runnable r) {
                        return new Thread(r, LOG_TAG + "Thread");
                    }
                });
        executor.allowCoreThreadTimeOut(true);
        this.executor = executor;
    }

    private void addHeaders(Request.Builder builder, @NonNull ArrayMap<String, String> headers) {
        for (String key : headers.keySet()) {
            builder.addHeader(key, headers.get(key));
        }
    }

    public void get(@NonNull String serviceUrl, @NonNull RestClientListener restClientListener) {
        get(serviceUrl, null, null, restClientListener);
    }

    public void get(@NonNull String serviceUrl, ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        get(serviceUrl, null, params, restClientListener);
    }

    public void get(@NonNull String serviceUrl, ArrayMap<String, String> headers, ArrayMap<String, Object> params, RestClientListener restClientListener) {
        final Request.Builder builder = new Request.Builder();
        if (headers != null)
            addHeaders(builder, headers);
        builder.url(generateUrlParams(serviceUrl, params));
        execute(builder, restClientListener);
    }

    public void post(@NonNull String serviceUrl, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        post(serviceUrl, null, params, restClientListener);
    }

    public void post(@NonNull String serviceUrl, ArrayMap<String, String> headers, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        final Request.Builder builder = new Request.Builder();
        if (headers != null)
            addHeaders(builder, headers);
        StringBuffer urls = new StringBuffer();
        if (defaultBaseUrl.length() > 0)
            urls.append(defaultBaseUrl);
        urls.append(serviceUrl);
        builder.url(urls.toString());
        builder.post(generateRequestBody(params));
        execute(builder, restClientListener);
    }

    public void put(@NonNull String serviceUrl, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        put(serviceUrl, null, params, restClientListener);
    }

    public void put(@NonNull String serviceUrl, ArrayMap<String, String> headers, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        final Request.Builder builder = new Request.Builder();
        if (headers != null)
            addHeaders(builder, headers);
        StringBuffer urls = new StringBuffer();
        if (defaultBaseUrl.length() > 0)
            urls.append(defaultBaseUrl);
        urls.append(serviceUrl);
        builder.url(urls.toString());
        builder.put(generateRequestBody(params));
        execute(builder, restClientListener);
    }

    public void delete(@NonNull String serviceUrl, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        delete(serviceUrl, null, params, restClientListener);
    }

    public void delete(@NonNull String serviceUrl, ArrayMap<String, String> headers, @NonNull ArrayMap<String, Object> params, @NonNull RestClientListener restClientListener) {
        final Request.Builder builder = new Request.Builder();
        if (headers != null)
            addHeaders(builder, headers);
        StringBuffer urls = new StringBuffer();
        if (defaultBaseUrl.length() > 0)
            urls.append(defaultBaseUrl);
        urls.append(serviceUrl);
        builder.url(urls.toString());
        builder.delete(generateRequestBody(params));
        execute(builder, restClientListener);
    }

    public void postMultipart(@NonNull String serviceUrl, @NonNull ArrayMap<String, File> files, @NonNull RestClientListener restClientListener) {
        postMultipart(serviceUrl, null, null, files, restClientListener);
    }

    public void postMultipart(@NonNull String serviceUrl, ArrayMap<String, Object> params, @NonNull ArrayMap<String, File> files, @NonNull RestClientListener restClientListener) {
        postMultipart(serviceUrl, null, params, files, restClientListener);
    }

    public void postMultipart(@NonNull String serviceUrl, ArrayMap<String, String> headers, ArrayMap<String, Object> params, @NonNull ArrayMap<String, File> files, @NonNull RestClientListener restClientListener) {
        final Request.Builder builder = new Request.Builder();
        if (headers != null)
            addHeaders(builder, headers);
        StringBuffer urls = new StringBuffer();
        if (defaultBaseUrl.length() > 0)
            urls.append(defaultBaseUrl);
        urls.append(serviceUrl);
        builder.url(urls.toString());
        builder.post(generateMultipartBody(params, files));
        execute(builder, restClientListener);
    }

    private void execute(final Request.Builder builder, final RestClientListener restClientListener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).build();
                try {
                    System.setProperty("http.keepAlive", "false");
                    final Response response = client.newCall(builder.build()).execute();
                    final String responseData = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (response.code() == 200) {
                                Type responseType=ReflectionHelper.getTypeArgument(restClientListener, 0);
                                callRequestCallbackOnSuccess(restClientListener, responseType != null ? new Gson().fromJson(responseData, responseType) : null);
                            } else {
                                Type errorType=ReflectionHelper.getTypeArgument(restClientListener, 1);
                                callRequestCallbackOnError(restClientListener, errorType != null ? new Gson().fromJson(responseData, errorType) : null);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Type errorType=((RestClientCallbackTypes) restClientListener).getErrorType();
                            restClientListener.onError("APIs not working...");
                        }
                    });
                }
            }
        });
    }

    private <E> void callRequestCallbackOnSuccess(final RestClientListener restClientListener, E responseParsedData)
    {
        if (restClientListener != null)
        {
            restClientListener.onSuccess(responseParsedData);
        }
    }

    private <E> void callRequestCallbackOnError(final RestClientListener restClientListener,E errorObject)
    {
        if (restClientListener != null)
        {
            restClientListener.onError(errorObject);
        }
    }

    private String generateUrlParams(String serviceUrl, ArrayMap<String, Object> params) {
        final StringBuffer urls = new StringBuffer();
        if (defaultBaseUrl.length() > 0)
            urls.append(defaultBaseUrl);
        urls.append(serviceUrl);
        if (params != null) {
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0) {
                    urls.append("?" + key + "=" + params.get(key));
                } else {
                    urls.append("&" + key + "=" + params.get(key));
                }
                i++;
            }
        }
        return urls.toString();
    }

    private RequestBody generateRequestBody(ArrayMap<String, Object> params) {
        final JSONObject jsonObj = new JSONObject();
        if (params != null) {
            for (String key : params.keySet()) {
                try {
                    jsonObj.put(key, params.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObj));
        return requestBody;
    }

    private RequestBody generateMultipartBody(ArrayMap<String, Object> params, ArrayMap<String, File> files) {
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, String.valueOf(params.get(key)));
            }
        }
        if (files != null) {
            for (String key : files.keySet()) {
                builder.addFormDataPart(key, key, RequestBody.create(MediaType.parse("image/png"), files.get(key)));
            }
        }
        return builder.build();
    }
}
