package com.example.penisriwahyu.pointerb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.penisriwahyu.pointerb.Model.Bab;
import com.example.penisriwahyu.pointerb.Model.Kelas;
import com.example.penisriwahyu.pointerb.Model.Mapel;
import com.example.penisriwahyu.pointerb.tabs.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class BabActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private Bundle bundle;
    private String kelas;
    private String nama_mapel;
    private int id_mapel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bab);

        toolbar = (Toolbar) findViewById(R.id.app_bar2);
        setSupportActionBar(toolbar);


        bundle = getIntent().getExtras();
        id_mapel = bundle.getInt("id_mapel");
        nama_mapel = bundle.getString("nama_mapel");
        kelas = bundle.getString("kelas");
        BabActivity.this.setTitle(nama_mapel + " " + kelas);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), bundle,this));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bab_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if( id == R.id.home){
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        String[] tabs;
        int id_mapel;
        Bundle bundle;
        public MyPagerAdapter(FragmentManager fm, Bundle bundle, Context context) {
            super(fm);
            DatabaseHandler db = new DatabaseHandler(context);
            this.bundle = bundle;
            List <Kelas> kelass = db.getKelas(bundle.getString("nama_kelas"));
            this.id_mapel = bundle.getInt("id_mapel");
            List<String> tabsList= new ArrayList<String>();
            for(int i=1;i<=kelass.size();i++){
                tabsList.add(bundle.getString("kelas") + " "+ i);
            }
            tabs = tabsList.toArray(new String[tabsList.size()]);
        }

        @Override
        public Fragment getItem(int position) {
            bundle.putInt("position",position);
            MyFragment myFragment = MyFragment.getInstance(bundle);
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }

    public static class MyFragment extends Fragment{
        private TextView textView;
        private LinearLayout rel;
        private String[] panel1;
        private View layout;
        private Button but1;
        private static int id_kelas;
        List<Bab> babs;
        List<Mapel> mapels;

        public static MyFragment getInstance(Bundle b){
            MyFragment myFragment= new MyFragment();
            Bundle bundle = new Bundle(b);
            myFragment.setArguments(bundle);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            Bundle bundle = getArguments();
            int offset;
            layout =inflater.inflate(R.layout.fragment_my,(ViewGroup) this.getView(), true);
            rel = (LinearLayout) layout.findViewById(R.id.linearLayout);

            rel.setPadding(10,10,10,10);
            if(bundle.getString("kelas").equals("SD")){
                offset=1;
            }
            else if(bundle.getString("kelas").equals("SMP")){
                offset=7;
            }
            else offset= 10;
            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());

            Log.d("Fragment Bab",bundle.getInt("position") + " " + bundle.get("nama_mapel"));
            babs = db.getBab(bundle.getInt("position") +offset ,bundle.getString("nama_mapel"));
            for (int i = 0;i < babs.size();i++){
                createBtn(babs.get(i).getNamaBab(),babs.get(i).getIdBab());
            }

            return layout;
        }

        public void createBtn(final String nama_bab, final int id_bab){
            Button button = new Button(getActivity());
            button.setText(nama_bab);
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
                    Intent i = new Intent(getActivity(), subBabView.class);
                    Bundle arg = new Bundle();
                    arg.putInt("id_bab",id_bab);
                    arg.putString("nama_bab",nama_bab);
                    i.putExtras(arg);
                    startActivity(i);
                }
            });

            rel.addView(button, params);
        }
    }
}
