package com.example.penisriwahyu.pointerb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    public int nomor = 0;
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        Button but = (Button) view.findViewById(R.id.sd);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(),MapelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nama_kelas","Sekolah Dasar");
                bundle.putString("kelas","SD");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        return view;
    }
}
