package com.rxmediandroidapp.fragments.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.activities.PatientSignup;

import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.Home;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.ArrayList;


public class Doctor_Details extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    EditText doc_attender_edtx;

    RecyclerView doc_details_recyler;
    private ListPopupWindow listPopupWindow;
    String[] doctrslist = {"Naresh","Mahesh","Suresh"};

    String[] tabslist = {"Patients List","Prescriptions List","Test Suggested List"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.doctor_details,null,false );
        StoredObjects.page_type="doctor_details";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        listPopupWindow = new ListPopupWindow(getActivity());
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        doc_details_recyler = v.findViewById( R.id. doc_details_recyler);
        doc_attender_edtx = v.findViewById( R.id. doc_attender_edtx);

        title_txt.setText( "Doctor Details" );


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

        doc_attender_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsignDctrsLstPopup ((CustomEditText) doc_attender_edtx);
            }
        });




        StoredObjects.getrray(tabslist);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        doc_details_recyler.setLayoutManager(linearLayoutManager);

        doc_details_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.menuitems_list,"doc_details",doc_details_recyler,R.layout.doc_details_listitem));



    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }

    private void AsignDctrsLstPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.drpdwn_lay,doctrslist));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(doctrslist[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }




}


