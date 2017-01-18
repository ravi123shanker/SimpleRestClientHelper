package com.ravi.restclient;

/**
 * Created by ryadav3 on 12/7/2016.
 */

public abstract class ProgressListener<R, E> {
    protected abstract void onSuccess(R response);
    protected abstract void onError(E error);
    protected abstract void onProgressUpdate(int progress);
}
