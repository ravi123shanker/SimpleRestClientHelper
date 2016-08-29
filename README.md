# SimpleRestClientHelper
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleRestClientHelper-green.svg?style=true)](https://android-arsenal.com/details/1/4242)

Simplest library to implement  rest client APIs using okhttp3 in your app

#USAGE

**Get Request**
~~~java
RestClientHelper.getInstance().get("httt://YOUR_API_URL", new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                // TODO: with response
            }

            @Override
            public void onError(String error) {
                // TODO: with error
            }
        });
~~~

**Post Request**
~~~java
HashMap<String, Object> postParams=new HashMap<>();
        postParams.put("Key1", "Value1");
        postParams.put("Key2", "Value2");
        RestClientHelper.getInstance().post("httt://YOUR_API_URL", postParams, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                // TODO: with response
            }

            @Override
            public void onError(String error) {
                // TODO: with error
            }
        });
 ~~~
 
 **Put Request**
~~~java
HashMap<String, Object> putParams=new HashMap<>();
        putParams.put("Key1", "Value1");
        putParams.put("Key2", "Value2");
        RestClientHelper.getInstance().put("httt://YOUR_API_URL", putParams, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                // TODO: with response 
            }

            @Override
            public void onError(String error) {
                // TODO: with error 
            }
        });
 ~~~
 
 **Delete Request**
~~~java
HashMap<String, Object> deleteParams=new HashMap<>();
        deleteParams.put("Key1", "Value1");
        deleteParams.put("Key2", "Value2");
        RestClientHelper.getInstance().delete("httt://YOUR_API_URL", deleteParams, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                // TODO: with response
            }

            @Override
            public void onError(String error) {
                // TODO: with error
            }
        });
 ~~~
 
 **Multipart Request**
 ~~~java
 HashMap<String, Object> multipartParams=new HashMap<>();
        multipartParams.put("Key1", "Value1");
        multipartParams.put("Key2", "Value2");
        HashMap<String, File> fileParams=new HashMap<>();
        File imageFile=new File("IMAGE_PATH");
        fileParams.put("KEY_NAME", imageFile);
        RestClientHelper.getInstance().postMultipart("httt://YOUR_API_URL", multipartParams, fileParams, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                // TODO: with response
            }

            @Override
            public void onError(String error) {
                // TODO: with error
            }
        });
  ~~~
  
  **Request with Headers**
  ~~~java
  HashMap<String, String> headers=new HashMap<>();
  headers.put("KEY_HEADER1", "VALUE_HEADER1");
  headers.put("KEY_HEADER2", "VALUE_HEADER2");
  ~~~
  Pass as a parameter in required methods like get, post, put, delete and postMultipart.
