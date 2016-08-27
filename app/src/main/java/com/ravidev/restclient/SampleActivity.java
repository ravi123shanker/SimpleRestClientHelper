package com.ravidev.restclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by ravishankeryadav on 8/27/2016.
 */
public class SampleActivity extends AppCompatActivity {
    TextView txtRequestOne;
    TextView txtRequestTwo;
    TextView txtRequestThree;

    private String baseUrl="http://www.json-generator.com/api/";
    private String serviceUrl1="json/get/ckEUAGRlki?indent=2";
    private String serviceUrl2="json/get/bVDTzIQrWW?indent=2";
    private String serviceUrl3="json/get/bUcbFrMrNe?indent=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtRequestOne=(TextView) findViewById(R.id.txtRequestOne);
        txtRequestTwo=(TextView) findViewById(R.id.txtRequestTwo);
        txtRequestThree=(TextView) findViewById(R.id.txtRequestThree);
        RestClientHelper.defaultBaseUrl=baseUrl;
        callRequestOne();
        callRequestTwo();
        callRequestThree();

    }

    private void callRequestOne(){
        RestClientHelper.getInstance().get(serviceUrl1, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                txtRequestOne.setText(response);
            }

            @Override
            public void onError(String error) {
                txtRequestOne.setText(error);
            }
        });
    }
    private void callRequestTwo(){
        RestClientHelper.getInstance().get(serviceUrl2, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                txtRequestTwo.setText(response);
            }

            @Override
            public void onError(String error) {
                txtRequestThree.setText(error);
            }
        });
    }
    private void callRequestThree(){
        RestClientHelper.getInstance().get(serviceUrl3, new RestClientHelper.RestClientListener() {
            @Override
            public void onSuccess(String response) {
                txtRequestThree.setText(response);
            }

            @Override
            public void onError(String error) {
                txtRequestThree.setText(error);
            }
        });
    }


}
