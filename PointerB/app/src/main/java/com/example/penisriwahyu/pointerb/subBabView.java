package com.example.penisriwahyu.pointerb;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.penisriwahyu.pointerb.Model.Subbab;

import java.util.ArrayList;
import java.util.List;


public class subBabView extends ActionBarActivity {
    private Toolbar toolbar;
    private LinearLayout rel;
    private List<Subbab> subbabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bund = intent.getExtras();
        setContentView(R.layout.activity_sub_bab_view);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        rel = (LinearLayout) findViewById(R.id.subBab);
        subBabView.this.setTitle(bund.getString("nama_bab"));

        DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
        subbabs = db.getSubbab(bund.getInt("id_bab"));
        for (int i = 0;i < subbabs.size();i++){
            createBtn(subbabs.get(i).getNamaSubbab(),subbabs.get(i).getIdSubbab());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_bab_view, menu);
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

    public void createBtn(final String nama_subbab, final int id_subbab){
        Button button = new Button(this);
        button.setText(nama_subbab);
        button.setTextSize(18);
        button.setBackground(getResources().getDrawable(R.drawable.btn_state));
        button.setTextColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 10);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(subBabView.this, materiViewer.class);
                Bundle arg = new Bundle();
                arg.putInt("id_subbab",id_subbab);
                arg.putString("nama_subbab",nama_subbab);
                i.putExtras(arg);
                startActivity(i);
            }
        });

        rel.addView(button, params);
    }
}
