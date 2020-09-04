package com.rxmediandroidapp.activities;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomBoldTextView;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;

import java.util.Calendar;

public class HosptalSignup extends Activity {

    ImageView h_sgnup_back_img;
    CustomEditText h_sgnup_hsptlnme_edtx,h_sgnup_email_edtx,h_sgnup_hsptlrgstno_edtx,h_sgnup_beds_edtx,h_sgnup_mbile_edtx,
            h_sgnup_adres_edtx,h_sgnup_avabletme_edtx,h_sgnup_tmetwo_edtx,h_sgnup_pswd_edtx,h_sgnup_cnfmpswd_edtx;
    CustomButton h_sgnup_btn;

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
        setContentView(R.layout.hosptal_signup);

        initialisation();
    }

    public void initialisation() {

        h_sgnup_back_img =  findViewById(R.id.h_sgnup_back_img);
        h_sgnup_avabletme_edtx =  findViewById(R.id.h_sgnup_avabletme_edtx);
        h_sgnup_tmetwo_edtx =  findViewById(R.id.h_sgnup_tmetwo_edtx);

        h_sgnup_btn =  findViewById(R.id.h_sgnup_btn);

        h_sgnup_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HosptalSignup.this.finish();
            }
        });


        h_sgnup_avabletme_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(HosptalSignup.this,
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);

                                h_sgnup_avabletme_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        h_sgnup_tmetwo_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(HosptalSignup.this,
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);

                                h_sgnup_tmetwo_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        h_sgnup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HosptalSignup.this, SideMenu.class));
            }
        });

    }
}
