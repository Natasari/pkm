package com.example.penisriwahyu.pointerb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment implements View.OnClickListener{


    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        return view;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this.getActivity(), "Button is clicked fragment 3!", Toast.LENGTH_LONG).show();
        //startActivity(new Intent(getActivity(), SubActivity.class));
    }
}
