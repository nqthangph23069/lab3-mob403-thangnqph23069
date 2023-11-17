package com.example.lab3.bai1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetContact extends AsyncTask<Void, Void, Void> {
    private String TAG = Bai1Activity.class.getSimpleName();
    public static String url = "http://192.168.56.1/api_android/";
    ArrayList<Contact> list;
    private ProgressDialog progressDialog;
    private ListView lv;
    Context context;
    ContactAdapter adapter;

    public GetContact(ListView lv, Context context) {
        this.lv = lv;
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null){
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray contacts = jsonObject.getJSONArray("contacts");
                // looping through all Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                    String name = c.getString("name");
                    String email = c.getString("email");
                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    Contact contact = new Contact(name, email, mobile);
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setMobile(mobile);
                    list.add(contact);
                }
            }catch (Exception e){
                e.printStackTrace();
                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }
        }else{
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        adapter = new ContactAdapter(context, list);
        lv.setAdapter(adapter);
    }
}
