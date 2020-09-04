package com.rxmediandroidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class F_PatientList_Two extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;

    RecyclerView f_patient_recyler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.f_patientlist_two,null,false );
        StoredObjects.page_type="f_patientlist_two";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);

        f_patient_recyler = v.findViewById( R.id.f_patient_recyler);
        title_txt.setText( "Patient List" );




        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home() );
            }
        } );







        StoredObjects.hashmaplist(5);
       // customRecyclerview.Assigndatatorecyleviewhashmap( marketing_exicutive_recyler, StoredObjects.dummy_list,"marketing_exicutive", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.marketing_exicutive_listitem);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        f_patient_recyler.setLayoutManager(linearLayoutManager);

        f_patient_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"f_patient",f_patient_recyler,R.layout.f_patientlist_two_listitem));


    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}

