package com.example.penisriwahyu.pointerb;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.penisriwahyu.pointerb.Model.Mapel;

import java.util.List;


public class MapelActivity extends ActionBarActivity {
    private LinearLayout layout;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        bundle = getIntent().getExtras();
        String title = bundle.getString("nama_kelas");
        this.setTitle(title);
        Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        layout = (LinearLayout) findViewById(R.id.layoutMapel);
        DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
        List<Mapel> mapels= db.getMapelByKelas(title);
        for(Mapel mapel : mapels){
            createBtn(mapel.getIdMapel(),mapel.getNamaMapel());
        }


    }

    public void createBtn(final int id_mapel, final String nama_mapel){
        Button button = new Button(this);
        button.setText(nama_mapel);
        button.setTextSize(18);
        button.setTag(R.id.key_1, id_mapel);
        button.setTag(R.id.key_2,nama_mapel);
        button.setBackground(getResources().getDrawable(R.drawable.btn_state));
        button.setTextColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.weight = 1;
        params.setMargins(10, 10, 10, 10);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MapelActivity.this, BabActivity.class);
                bundle.putInt("id_mapel", Integer.parseInt(v.getTag(R.id.key_1).toString()));
                bundle.putString("nama_mapel",v.getTag(R.id.key_2).toString());

                i.putExtras(bundle);
                startActivity(i);
            }
        });

        layout.addView(button, params);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mapel_activity, menu);
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
        if (id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
