# SimpleRestClientHelper
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleRestClientHelper-green.svg?style=true)](https://android-arsenal.com/details/1/4242)

Simplest library to implement  rest client APIs using okhttp3 in your app

#USAGE

**Get Request**
~~~java
RestClientHelper.getInstance().get(serviceUrl1, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                //todo with response
            }

            @Override
            public void onError(String error) {
                //todo with error
            }
        });
~~~
