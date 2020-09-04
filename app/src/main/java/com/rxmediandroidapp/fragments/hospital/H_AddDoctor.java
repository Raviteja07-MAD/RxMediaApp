package com.rxmediandroidapp.fragments.hospital;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.doctor.Doctor_Details;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class H_AddDoctor extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_adddoctor,null,false );
        StoredObjects.page_type="add_doctor";
        StoredObjects.back_type="add_doctor";
        SideMenu.updatemenu(StoredObjects.page_type);

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Add Assistant");
        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

    }


    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


}

