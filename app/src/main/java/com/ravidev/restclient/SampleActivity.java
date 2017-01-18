package com.ravidev.restclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ravi.restclient.RestClientHelper;
import com.ravi.restclient.RestClientListener;

import java.util.List;

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
        RestClientHelper.getInstance().get(serviceUrl1, new RestClientListener<Employee, Error>() {
            @Override
            public void onSuccess(Employee response) {
                txtRequestOne.setText(response.toString());
            }

            @Override
            public void onError(Error error) {
                txtRequestOne.setText(error.toString());
            }
        });
    }
    private void callRequestTwo(){
        RestClientHelper.getInstance().get(serviceUrl2, new RestClientListener<List<Employee>, Error>() {
            @Override
            public void onSuccess(List<Employee> response) {
                StringBuffer stringBuffer=new StringBuffer();
                for (Employee employee: response){
                    stringBuffer.append(employee.toString()+"\n");
                }
                txtRequestTwo.setText(stringBuffer.toString());
            }

            @Override
            public void onError(Error error) {
                txtRequestThree.setText(error.toString());
            }
        });
    }
    private void callRequestThree(){
        RestClientHelper.getInstance().get(serviceUrl3, new RestClientListener<List<Employee>, Error>() {
            @Override
            public void onSuccess(List<Employee> response) {
                StringBuffer stringBuffer=new StringBuffer();
                for (Employee employee: response){
                    stringBuffer.append(employee.toString()+"\n");
                }
                txtRequestThree.setText(stringBuffer.toString());
            }

            @Override
            public void onError(Error error) {
                txtRequestThree.setText(error.toString());
            }
        });
    }


}
