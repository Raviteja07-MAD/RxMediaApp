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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;

import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomNormalButton;
import com.rxmediandroidapp.storedobjects.StoredObjects;

public class Franchises_Details extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;

    RecyclerView franchise_recyler,franchise_one_recyler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.franchises_details,null,false );
        StoredObjects.page_type="franchises_details";
        StoredObjects.back_type="franchises_details";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);


        franchise_recyler = v.findViewById( R.id. franchise_recyler);
        franchise_one_recyler= v.findViewById( R.id. franchise_one_recyler);

        title_txt.setText("Franchises List");

        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });


        StoredObjects.hashmaplist(4);

        //customRecyclerview.Assigndatatorecyleviewhashmap( franchise_recyler, StoredObjects.dummy_list,"franchise", StoredObjects.Gridview, 2, StoredObjects.ver_orientation, R.layout.franchises_details_listitem);
        final GridLayoutManager linearLayoutManager=new GridLayoutManager(getActivity(),2);
        franchise_recyler.setLayoutManager(linearLayoutManager);
        franchise_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"franchise",franchise_one_recyler,R.layout.franchises_details_listitem));



        StoredObjects.hashmaplist(4);
        final LinearLayoutManager linearLayoutManagerone=new LinearLayoutManager(getActivity());
        franchise_one_recyler.setLayoutManager(linearLayoutManagerone);

        franchise_one_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"franchise_one",franchise_one_recyler,R.layout.franchises_detailsone_listitem));


    }

    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();
    }

}

