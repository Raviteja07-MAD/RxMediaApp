package com.rxmediandroidapp.fragments.patient;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.activities.PatientSignup;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.Add_Appointment_One;
import com.rxmediandroidapp.fragments.Home;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class AddPatient extends Fragment {

    CustomEditText apnmnt_nme_edtx,apnmnt_email_edtx,apnmnt_mbile_edtx,apnmnt_adhar_edtx,
            apnmnt_dob_edtx,apnmnt_gender_edtx,apnmnt_bldgrup_edtx,apnmnt_cnsltdctr_edtx;

    ImageView backbtn_img;
    TextView title_txt;
    CustomButton apnmnt_adapntmnt_btn;

    private ListPopupWindow listPopupWindow;
    String[] genderlist = {"MALE","FEMALE"};

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.add_apntmnt_one,null,false );
        StoredObjects.page_type="add_apntmnt";
        StoredObjects.back_type="add_apntmnt";
        SideMenu.updatemenu(StoredObjects.page_type);
        listPopupWindow = new ListPopupWindow(getActivity());
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt= v.findViewById( R.id.title_txt);
        title_txt.setText( "ADD PATIENT" );

        apnmnt_nme_edtx = v.findViewById(R.id.apnmnt_nme_edtx);
        apnmnt_email_edtx= v.findViewById( R.id.apnmnt_email_edtx);
        apnmnt_mbile_edtx = v.findViewById(R.id.apnmnt_mbile_edtx);
        apnmnt_adhar_edtx= v.findViewById( R.id.apnmnt_adhar_edtx);
        apnmnt_dob_edtx = v.findViewById(R.id.apnmnt_dob_edtx);
        apnmnt_gender_edtx= v.findViewById( R.id.apnmnt_gender_edtx);
        apnmnt_bldgrup_edtx = v.findViewById(R.id.apnmnt_bldgrup_edtx);
        apnmnt_cnsltdctr_edtx= v.findViewById( R.id.apnmnt_cnsltdctr_edtx);

        apnmnt_adapntmnt_btn = v.findViewById(R.id.apnmnt_adapntmnt_btn);

        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Home() );

            }
        } );


        apnmnt_dob_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                apnmnt_dob_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        apnmnt_gender_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenderListPopup (apnmnt_gender_edtx);
            }
        });

        apnmnt_adapntmnt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentcallinglay(new Add_Appointment_One());

            }
        });
    }

    private void GenderListPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.drpdwn_lay,genderlist));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(genderlist[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


}
