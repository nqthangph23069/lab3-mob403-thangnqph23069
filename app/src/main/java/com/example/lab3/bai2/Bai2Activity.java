package com.example.lab3.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab3.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Bai2Activity extends AppCompatActivity {

    private String urlJsonObj = "http://192.168.57.2/api_android/person_object.json";
    private String urlJsonArr = "http://192.168.57.2/api_android/person_array.json";
    private static String TAG = Bai2Activity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private Button btnObj, btnArr;
    private TextView tv;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        btnArr = findViewById(R.id.btnJsonArr);
        btnObj = findViewById(R.id.btnJsonOb);
        tv = findViewById(R.id.tvJson);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCancelable(false);
        btnArr.setOnClickListener(v -> {
            makeJsonArrayRequest();
        });
        btnObj.setOnClickListener(v -> {
            makeJsonObjectRequest();
        });
    }

    private void makeJsonObjectRequest() {
        showDialog();
        JsonObjectRequest objectRequest = new JsonObjectRequest(urlJsonObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d(TAG, jsonObject.toString());
                        try {
                            jsonResponse = "";
                            String name = jsonObject.getString("name");
                            String email = jsonObject.getString("email");
                            JSONObject phone = jsonObject.getJSONObject("phone");
                            String home = phone.getString("home");
                            String mobile = phone.getString("mobile");
                            jsonResponse += "Name: " + name + "\n\n";
                            jsonResponse += "Email: " + email + "\n\n";
                            jsonResponse += "Home: " + home + "\n\n";
                            jsonResponse += "Mobile: " + mobile + "\n\n";
                            tv.setText(jsonResponse);
                            hideDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    private void makeJsonArrayRequest() {
        showDialog();

        JsonArrayRequest arrayRequest = new JsonArrayRequest(urlJsonArr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        Log.d(TAG, jsonArray.toString());
                        try {
                            // Parsing json array response
// loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject person = (JSONObject) jsonArray.get(i);
                                String name = person.getString("name");
                                String email = person.getString("email");
                                JSONObject phone = person.getJSONObject("phone");
                                String home = phone.getString("home");
                                String mobile = phone.getString("mobile");
                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Email: " + email + "\n\n";
                                jsonResponse += "Home: " + home + "\n\n";
                                jsonResponse += "Mobile: " + mobile + "\n\n";
                            }
                            tv.setText(jsonResponse);
                            hideDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void showDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
}