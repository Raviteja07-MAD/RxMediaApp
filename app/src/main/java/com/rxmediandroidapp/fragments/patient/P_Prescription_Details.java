package com.rxmediandroidapp.fragments.patient;

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

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.fragments.doctor.Doc_Patient_Details;
import com.rxmediandroidapp.fragments.hospital.H_Patient_Details;
import com.rxmediandroidapp.storedobjects.StoredObjects;


public class P_Prescription_Details extends Fragment {

    ImageView backbtn_img;
    TextView title_txt,otherdoc_txt;
    LinearLayout prescription_lay;
    ImageView document_img;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.patient_prescription_details,null,false );
        StoredObjects.page_type="prescription_details";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        otherdoc_txt = v.findViewById( R.id. otherdoc_txt);
        document_img = v.findViewById( R.id. document_img);
        prescription_lay = v.findViewById( R.id. prescription_lay);

        title_txt.setText( "Prescription Details" );

        prescription_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // fragmentcallinglay(new H_Patient_Details());

            }
        });

        document_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //fragmentcallinglay(new Doc_Patient_Details());

            }
        });



        otherdoc_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // fragmentcallinglay(new Members_Details());

            }
        });


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });




    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


