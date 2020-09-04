package com.rxmediandroidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.rxmediandroidapp.fragments.doctor.Doctor_Details;
import com.rxmediandroidapp.storedobjects.StoredObjects;


public class Add_Appointment extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView addapointment_recycle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.add_appointment,null,false );
        StoredObjects.page_type="add_appointment";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        addapointment_recycle = v.findViewById( R.id. addapointment_recycle);
        title_txt.setText( "Add Appointment" );


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home() );
            }
        } );

        StoredObjects.hashmaplist(5);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        addapointment_recycle.setLayoutManager(linearLayoutManager);

        addapointment_recycle.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"addapointment",addapointment_recycle,R.layout.add_appointment_listitems));



    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


