package com.rxmediandroidapp.fragments.patient;

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

public class Members_Details extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;

    RecyclerView prescription_recyler,doc_prescription_recyler;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.p_members_details,null,false );
        StoredObjects.page_type="members_details";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        prescription_recyler = v.findViewById( R.id. prescription_recyler);
        doc_prescription_recyler = v.findViewById(R.id.doc_prescription_recyler);

        title_txt.setText( "Members Details" );


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });


        StoredObjects.hashmaplist(2);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        prescription_recyler.setLayoutManager(linearLayoutManager);

        prescription_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"prescription",prescription_recyler,R.layout.members_details_lstitem));

        final LinearLayoutManager linearLayoutManagerone=new LinearLayoutManager(getActivity());
        doc_prescription_recyler.setLayoutManager(linearLayoutManagerone);
        doc_prescription_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"doc_prescription",doc_prescription_recyler,R.layout.doc_prescription_lstitem));





    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}

