package com.rxmediandroidapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.fragments.doctor.Doctor_Details;
import com.rxmediandroidapp.storedobjects.StoredObjects;


public class Add_Appointment_One extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    LinearLayout addphy_lay;
    Button addapp_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.add_appointment_one,null,false );
        StoredObjects.page_type="add_appointment_one";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        addphy_lay = v.findViewById( R.id. addphy_lay);
        addapp_btn = v.findViewById( R.id. addapp_btn);
        title_txt.setText( "Dummy Screen" );


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home() );
            }
        } );

        addphy_lay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                physicalexampopup (getActivity());
            }
        } );

        addapp_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Add_Apntmnt_Three() );
               // Diagnosepopup(getActivity());
            }
        } );


    }
    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }
    private void physicalexampopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_physical_examination );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }



}


