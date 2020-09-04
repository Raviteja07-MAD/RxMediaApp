package com.rxmediandroidapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;


public class Block_Doctor extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView blockdoctor_recycle;
    EditText test_date_edtx;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.block_doctor,null,false );
        StoredObjects.page_type="block_doctor";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        blockdoctor_recycle = v.findViewById( R.id. blockdoctor_recycle);

        title_txt.setText( "Block Doctors" );


        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentcallinglay( new Home() );
            }
        });



        StoredObjects.hashmaplist(2);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        blockdoctor_recycle.setLayoutManager(linearLayoutManager);
        blockdoctor_recycle.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"blockdoctor",blockdoctor_recycle,R.layout.block_doctor_listitems));




    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


