package com.rxmediandroidapp.fragments.doctor;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.hospital.H_PatentList;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class Doc_Profile extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    CustomButton d_prf_sbmt_btn;
    static RecyclerView d_prf_recyler;
    public static HashMapRecycleviewadapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.doc_profile,null,false );
        StoredObjects.page_type="doc_prfle";
        StoredObjects.back_type="doc_prfle";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {
        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt = v.findViewById(R.id.title_txt);
        d_prf_recyler = v.findViewById(R.id.d_prf_recyler);
        d_prf_sbmt_btn = v.findViewById(R.id.d_prf_sbmt_btn);

        title_txt.setText("Profile");
        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

        d_prf_sbmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // fragmentcallinglay(new H_PatentList());
            }
        });


        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        d_prf_recyler.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(3);
        adapter = new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"doc_prfle",d_prf_recyler,R.layout.doc_prfle_lstitem);
        d_prf_recyler.setAdapter(adapter);

    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }

}
