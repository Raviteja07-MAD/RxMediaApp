package com.rxmediandroidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.rxmediandroidapp.fragments.hospital.H_AddDoctor;
import com.rxmediandroidapp.storedobjects.StoredObjects;

public class Ass_PatientList extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView ass_patient_recycler;
   public static HashMapRecycleviewadapter adapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.ass_patientlist,null,false );
        StoredObjects.page_type="ass_patientlist";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        ass_patient_recycler = v.findViewById( R.id.ass_patient_recycler);

        title_txt.setText( "Patient List" );


        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });



        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        ass_patient_recycler.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(4);
        adapter=new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"ass_patientlist",ass_patient_recycler,R.layout.ass_patientlist_listitem);
        ass_patient_recycler.setAdapter(adapter);

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}



