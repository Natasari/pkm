package com.example.penisriwahyu.pointerb;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.example.penisriwahyu.pointerb.Model.Bab;
import com.example.penisriwahyu.pointerb.Model.Kelas;
import com.example.penisriwahyu.pointerb.Model.Mapel;
import com.example.penisriwahyu.pointerb.Model.Materi;
import com.example.penisriwahyu.pointerb.Model.PaketSoal;
import com.example.penisriwahyu.pointerb.Model.Soal;
import com.example.penisriwahyu.pointerb.Model.Subbab;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karsten on 09/03/2015.
 */
public class LoginOperator extends AsyncTask<String, Void, String> //1. parameter, 2. ???, 3. return type
{
    private Context context;
    private ProgressDialog pDialog;
    private String email;
    private String pass;
    public String status;

    // constructor
    public LoginOperator(Context ctx, String email, String pass){
        context = ctx;
        this.email = email;
        this.pass = pass;
    }
    protected String doInBackground(String... params)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://indohealth.esy.es/index.php/res/doLogin");

        List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
        parameters.add(new BasicNameValuePair("email", this.email));
        parameters.add(new BasicNameValuePair("pass", this.pass));

        Log.d("SSS",email+"------"+pass);
        String json = new String();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity);
                // Getting JSON Array node
                Log.d("SSS",json);

            }
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
        return json;
    }

    protected void onPreExecute() {
        pDialog = ProgressDialog.show(context, "", "Please Wait...", true, false);
    }

    protected void onPostExecute(String retval) {
        if (pDialog.isShowing())
            pDialog.dismiss();

        this.status = retval;
    }
    protected void onProgressUpdate(Void... values) {}
}
