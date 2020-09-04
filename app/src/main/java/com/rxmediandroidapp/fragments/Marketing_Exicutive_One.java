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
import com.rxmediandroidapp.storedobjects.StoredObjects;

public class Marketing_Exicutive_One extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;

    RecyclerView marketing_exicutiveone_recyler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.marketing_exicutive_one,null,false );
        StoredObjects.page_type="marketing_exicutive_one";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        marketing_exicutiveone_recyler = v.findViewById( R.id.marketing_exicutiveone_recyler);
        title_txt.setText( "Franchises List" );




        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home() );
            }
        } );




        StoredObjects.hashmaplist(4);
        //customRecyclerview.Assigndatatorecyleviewhashmap( marketing_exicutiveone_recyler, StoredObjects.dummy_list,"marketing_exicutiveone", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.marketing_exicutiveone_listitem);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        marketing_exicutiveone_recyler.setLayoutManager(linearLayoutManager);

        marketing_exicutiveone_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"marketing_exicutiveone",marketing_exicutiveone_recyler,R.layout.marketing_exicutiveone_listitem));


    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}

