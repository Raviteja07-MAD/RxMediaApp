package com.rxmediandroidapp.fragments.doctor;

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
import com.rxmediandroidapp.customfonts.CustomNormalButton;
import com.rxmediandroidapp.customfonts.CustomRegularTextView;
import com.rxmediandroidapp.storedobjects.StoredObjects;

public class Doc_Patient_List extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    CustomNormalButton d_today_btn,d_hstry_btn;
    static RecyclerView d_que_recyler,d_todaycheck_recyler;
    public static HashMapRecycleviewadapter adapter,adapterone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.doc_patient_list,null,false );
        StoredObjects.page_type="doc_patnt_lst";
        StoredObjects.back_type="doc_patnt_lst";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);

        d_today_btn = v.findViewById( R.id. d_today_btn);
        d_hstry_btn= v.findViewById( R.id. d_hstry_btn);
        d_que_recyler = v.findViewById( R.id. d_que_recyler);
        d_todaycheck_recyler= v.findViewById( R.id. d_todaycheck_recyler);

        title_txt.setText("Patient List");

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
        d_que_recyler.setLayoutManager(linearLayoutManager);

        final LinearLayoutManager linearLayoutManagerone =new LinearLayoutManager(getActivity());
        d_todaycheck_recyler.setLayoutManager(linearLayoutManagerone);

        StoredObjects.hashmaplist(1);
        adapter = new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"d_patnt_que",d_que_recyler,R.layout.h_patents_lstitem);
        d_que_recyler.setAdapter(adapter);

        StoredObjects.hashmaplist(3);
        adapterone = new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"d_patent_chcked",d_todaycheck_recyler,R.layout.h_patents_lstitem);
        d_todaycheck_recyler.setAdapter(adapterone);

    }

    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();
    }

}

