package com.ravi.restclient;

import java.lang.reflect.Type;

/**
 * Created by ryadav3 on 12/7/2016.
 */

public interface RestClientCallbackTypes {
     Type getResponseType();
     Type getErrorType();
}
