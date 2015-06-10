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
public class LongOperation extends AsyncTask<String, Void, Void> //1. parameter, 2. ???, 3. return type
{
    private Context context;
    private ProgressDialog pDialog;

    // constructor
    public LongOperation(Context ctx){
        context = ctx;
    }

    protected Void doInBackground(String... params)
    {
        DatabaseHandler db = new DatabaseHandler(context);
        String[] param = {"kelas","mapel","bab","subbab","materi","paket_soal","soal"};
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://128.199.140.247/pointschool/index.php/rest");

        for(String table : param){
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
            parameters.add(new BasicNameValuePair("table", table));
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
                    String json = EntityUtils.toString(entity);
                    Log.d("Http Post Response("+table+")", json);

                    // Getting JSON Array node
                    JSONArray jsonArr = new JSONArray(json);

                    // looping through All Contacts
                    for (int i = 0; i < jsonArr.length(); i++) {

                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        Gson gson = new Gson();
                        if(table.equals("kelas")){
                            Kelas kelas = gson.fromJson(jsonObj.toString(), Kelas.class);
                            db.createKelas(kelas);
                        }
                        else if(table.equals("mapel")){
                            Mapel mapel = gson.fromJson(jsonObj.toString(), Mapel.class);
                            db.createMapel(mapel);
                        }
                        else if(table.equals("bab")){
                            Bab bab = gson.fromJson(jsonObj.toString(), Bab.class);
                            Log.d("bab","bab");
                            db.createBab(bab);
                        }
                        else if(table.equals("subbab")){
                            Log.d("subbab","subbab");
                            Subbab subbab = gson.fromJson(jsonObj.toString(), Subbab.class);
                            db.createSubbab(subbab);
                        }
                        else if(table.equals("materi")){
                            Materi materi = gson.fromJson(jsonObj.toString(), Materi.class);
                            db.createMateri(materi);
                        }
                        else if(table.equals("paket_soal")){
                            PaketSoal paketsoal = gson.fromJson(jsonObj.toString(), PaketSoal.class);
                            db.createPaketSoal(paketsoal);
                        }
                        else if(table.equals("soal")){
                            Soal soal = gson.fromJson(jsonObj.toString(), Soal.class);
                            db.createSoal(soal);
                        }
                        // Log.d("JSON Object",jsonObj.toString());
                    }
                }

            } catch (ClientProtocolException e) {
                // Log exception
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    protected void onPreExecute() {
       pDialog = ProgressDialog.show(context, "", "Please Wait...", true, false);
    }

    protected void onPostExecute(Void unused) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    protected void onProgressUpdate(Void... values) {}
}
