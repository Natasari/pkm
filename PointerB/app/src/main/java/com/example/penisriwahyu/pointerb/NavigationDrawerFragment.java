package com.example.penisriwahyu.pointerb;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements PeniAdapter.ClickListener{


    public static final String PREF_FILE_NAME="test_pref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";

    private RecyclerView recyclerView;
    private View containerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private PeniAdapter adapter;
    private boolean mUserLearnedDrawer;
    private boolean mFormSavedInstanceState;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PeniAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //selectItem(mCurrentSelectedPosition);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.getBoolean(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if(savedInstanceState==null){
            mFormSavedInstanceState=true;
        }
    }



    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_content, R.drawable.ic_profile, R.drawable.ic_info};
        String[] titles = {"PointSchool", "Profile", "Info"};
        for(int i=0; i<icons.length && i<titles.length; i++){
            Information current = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

           @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView,slideOffset);
               toolbar.setAlpha(1-slideOffset/2);
            }
        };
        if(!mUserLearnedDrawer && !mFormSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        switch (position){
            case 0:
                FragmentOne frag = new FragmentOne();
                fr.replace(R.id.fragment_ku,frag);
                fr.addToBackStack(null);
                fr.commit();
                getActivity().setTitle("PointerSchool");
                mDrawerLayout.closeDrawer(containerView);
                break;
            case 1:
                FragmentThree frag3 = new FragmentThree();
                fr.replace(R.id.fragment_ku,frag3);
                fr.addToBackStack(null);
                fr.commit();
                getActivity().setTitle("Profile");
                mDrawerLayout.closeDrawer(containerView);
                break;
            case 2:
                FragmentTwo frag2 = new FragmentTwo();
                fr.replace(R.id.fragment_ku,frag2);
                fr.addToBackStack(null);
                fr.commit();
                getActivity().setTitle("Info");
                mDrawerLayout.closeDrawer(containerView);
                break;
            default:
                break;
        }
        

       // FragmentManager frg =

    }


}
