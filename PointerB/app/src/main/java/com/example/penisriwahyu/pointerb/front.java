package com.example.penisriwahyu.pointerb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class front extends ActionBarActivity {
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);

        Button but = (Button) this.findViewById(R.id.btnSingIn);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if (Email.matches("") || Password.matches("")) {
                    TextView textV = (TextView) findViewById(R.id.salah);
                    textV.setText("wrong email or password");
                }
                String status = new String();
                try {
                    LoginOperator task = new LoginOperator(front.this, Email, Password);
                    status = task.execute().get();
                    Log.d("BROOOOOOOOOOOOOOOO", status);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (status.equals("TRUE")){

                    //TARUH INTENTNYA DISINI
                }
//                String myEmail = "penitasari93@gmail.com";
//                String myPassword = "peni";
//
//                Log.d("PENI", myEmail + " | " + Email);
                //sendPostRequest(Email, Password);
//                if (Email.compareTo("penitasari93@gmail.com") == 0) {
//                    Log.d("PENI", "MATCHES");
//                } else {
//                    Log.d("PENI", "NOT");
//                }


                /*Intent intent = new Intent(front.this, login.class);
                startActivity(intent);
                finish();
                 Intent intent = new Intent(getActivity(),MapelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nama_kelas","Sekolah Dasar");
                bundle.putString("kelas","SD");
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


