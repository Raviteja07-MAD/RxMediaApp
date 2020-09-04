package com.rxmediandroidapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;

import androidx.annotation.Nullable;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;

import java.util.Calendar;


public class PatientSignup extends Activity {

    ImageView p_sgnup_back_img;
    CustomEditText p_sgnup_nme_edtx,p_sgnup_email_edtx,p_sgnup_mbile_edtx,p_sgnup_adhar_edtx,p_sgnup_dob_edtx,
            p_sgnup_gender_edtx,p_sgnup_bldgrup_edtx,p_sgnup_pswd_edtx,p_sgnup_cnfmpswd_edtx;
    CustomButton p_sgnup_btn;

    private ListPopupWindow listPopupWindow;
    String[] genderlist = {"Male","Female"};

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_signup);
        listPopupWindow = new ListPopupWindow(PatientSignup.this);
        initialisation();
    }

    public void initialisation() {

        p_sgnup_back_img =  findViewById(R.id.p_sgnup_back_img);
        p_sgnup_dob_edtx =  findViewById(R.id.p_sgnup_dob_edtx);
        p_sgnup_gender_edtx =  findViewById(R.id.p_sgnup_gender_edtx);
        p_sgnup_btn =  findViewById(R.id.p_sgnup_btn);

        p_sgnup_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatientSignup.this.finish();
            }
        });

        p_sgnup_dob_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(PatientSignup.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                p_sgnup_dob_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        p_sgnup_gender_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenderListPopup (p_sgnup_gender_edtx);
            }
        });

        p_sgnup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PatientSignup.this, SideMenu.class));
            }
        });


    }

    private void GenderListPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(PatientSignup.this,R.layout.drpdwn_lay,genderlist));
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


}
