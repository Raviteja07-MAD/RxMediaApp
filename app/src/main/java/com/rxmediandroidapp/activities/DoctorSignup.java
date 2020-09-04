package com.rxmediandroidapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;

import java.util.Calendar;


public class DoctorSignup extends Activity {

    ImageView d_sgnup_back_img;
    CustomEditText d_sgnup_nme_edtx,d_sgnup_email_edtx,d_sgnup_rgstno_edtx,d_sgnup_spclztn_edtx,d_sgnup_statbard_edtx,
            d_sgnup_yrrgstn_edtx,d_sgnup_mbile_edtx,d_sgnup_adres_edtx,d_sgnup_frmday_edtx,d_sgnup_today_edtx,d_sgnup_custm_edtx,
            d_sgnup_avabletme_edtx,d_sgnup_tmetwo_edtx,d_sgnup_pswd_edtx,d_sgnup_cnfmpswd_edtx;
    CustomButton d_sgnup_btn;

    private ListPopupWindow listPopupWindow;
    String[] spcliztnlist = {"Surgeon","Psychiatrist","Cardiologist","Dermatologist"};

    private ListPopupWindow listPopupWindowone;
    String[] dayslist = {"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};


    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int hour;
    int minute;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_signup);
        listPopupWindow = new ListPopupWindow(DoctorSignup.this);
        listPopupWindowone = new ListPopupWindow(DoctorSignup.this);
        initialisation();
    }

    public void initialisation() {

        d_sgnup_back_img =  findViewById(R.id.d_sgnup_back_img);
        d_sgnup_spclztn_edtx =  findViewById(R.id.d_sgnup_spclztn_edtx);
        d_sgnup_frmday_edtx =  findViewById(R.id.d_sgnup_frmday_edtx);
        d_sgnup_today_edtx =  findViewById(R.id.d_sgnup_today_edtx);
        d_sgnup_avabletme_edtx =  findViewById(R.id.d_sgnup_avabletme_edtx);
        d_sgnup_tmetwo_edtx =  findViewById(R.id.d_sgnup_tmetwo_edtx);

        d_sgnup_btn =  findViewById(R.id.d_sgnup_btn);

        d_sgnup_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoctorSignup.this.finish();
            }
        });

        d_sgnup_frmday_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppointmentListPopup (d_sgnup_frmday_edtx);
            }
        });

        d_sgnup_today_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppointmentListPopup (d_sgnup_today_edtx);
            }
        });

        d_sgnup_avabletme_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(DoctorSignup.this,
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);

                                d_sgnup_avabletme_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        d_sgnup_tmetwo_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(DoctorSignup.this,
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);
                                d_sgnup_tmetwo_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        d_sgnup_spclztn_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpeclizatnListPopup (d_sgnup_spclztn_edtx);
            }
        });

        d_sgnup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DoctorSignup.this, SideMenu.class));
            }
        });
    }

    private void SpeclizatnListPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(DoctorSignup.this,R.layout.drpdwn_lay,spcliztnlist));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(spcliztnlist[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }

    private void AppointmentListPopup(final CustomEditText prfilenme){
        listPopupWindowone.setAdapter(new ArrayAdapter<>(DoctorSignup.this,R.layout.drpdwn_lay,dayslist));
        listPopupWindowone.setAnchorView(prfilenme);
        listPopupWindowone.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindowone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(spcliztnlist[position]);
                listPopupWindowone.dismiss();

            }
        });

        listPopupWindowone.show();
    }


}
