# SimpleRestClientHelper
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleRestClientHelper-green.svg?style=true)](https://android-arsenal.com/details/1/4242)

Simplest library to implement  rest client APIs using okhttp3 in your app

#USAGE

**Get Request**
~~~java
RestClientHelper.getInstance().get("http://YOUR_API_URL", new RestClientListener<Response, Error>() {
            @Override
            public void onSuccess(Response response) {
                // TODO: with response
            }

            @Override
            public void onError(Error error) {
                // TODO: with error
            }
        });
~~~

**Post Request**
~~~java
ArrayMap<String, Object> postParams=new ArrayMap<>();
        postParams.put("Key1", "Value1");
        postParams.put("Key2", "Value2");
        RestClientHelper.getInstance().post("http://YOUR_API_URL", postParams, new RestClientListener<Response, Error>() {
                    @Override
                    public void onSuccess(Response response) {
                        // TODO: with response
                    }

                    @Override
                    public void onError(Error error) {
                         // TODO: with error
                     }
              });                                                                       
 ~~~
 
 **Put Request**
~~~java
ArrayMap<String, Object> putParams=new ArrayMap<>();
        putParams.put("Key1", "Value1");
        putParams.put("Key2", "Value2");
        RestClientHelper.getInstance().put("http://YOUR_API_URL", putParams, new RestClientListener<Response, Error>() {
                    @Override
                    public void onSuccess(Response response) {
                        // TODO: with response
                    }

                    @Override
                    public void onError(Error error) {
                         // TODO: with error
                     }
              });   
 
 **Delete Request**
~~~java
ArrayMap<String, Object> deleteParams=new ArrayMap<>();
        deleteParams.put("Key1", "Value1");
        deleteParams.put("Key2", "Value2");
        RestClientHelper.getInstance().delete("http://YOUR_API_URL", deleteParams, new RestClientListener<Response, Error>() {
                    @Override
                    public void onSuccess(Response response) {
                        // TODO: with response
                    }

                    @Override
                    public void onError(Error error) {
                         // TODO: with error
                     }
              });   
 ~~~
 
 **Multipart Request**
 ~~~java
 ArrayMap<String, Object> multipartParams=new ArrayMap<>();
        multipartParams.put("Key1", "Value1");
        multipartParams.put("Key2", "Value2");
        ArrayMap<String, File> fileParams=new ArrayMap<>();
        File imageFile=new File("IMAGE_PATH");
        fileParams.put("KEY_NAME", imageFile);
        RestClientHelper.getInstance().postMultipart("http://YOUR_API_URL", multipartParams, fileParams, new RestClientListener<Response, Error>() {
                     @Override
                    public void onSuccess(Response response) {
                        // TODO: with response
                    }

                    @Override
                    public void onError(Error error) {
                         // TODO: with error
                     }
              });   
  ~~~
  
  **Request with Headers**
  ~~~java
  ArrayMap<String, String> headers=new ArrayMap<>();
  headers.put("KEY_HEADER1", "VALUE_HEADER1");
  headers.put("KEY_HEADER2", "VALUE_HEADER2");
  ~~~
  Pass as a parameter in required methods like get, post, put, delete and postMultipart.
