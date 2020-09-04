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
import com.rxmediandroidapp.activities.DoctorSignup;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.doctor.Doctor_Details;
import com.rxmediandroidapp.fragments.patient.P_Test_Sugestions;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class H_AddAssistant extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    CustomButton ad_dctr_sbmt_btn;
    CustomEditText ad_dctr_nme_edtx,ad_dctr_mbile_edtx,ad_dctr_avabletme_edtx,ad_dctr_tmetwo_edtx,ad_dctr_asgndctr_edtx;
    private ListPopupWindow listPopupWindow;
    String[] doctrslist = {"Dr. Ravi","Dr. Pradeep Reddy","Dr. Abhishek"};

    Calendar calendar;
    int hour;
    int minute;
    int month;
    int dayOfMonth;
    TimePickerDialog timePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.add_doctor_one,null,false );
        StoredObjects.page_type="add_doctor";
        StoredObjects.back_type="add_doctor";
        SideMenu.updatemenu(StoredObjects.page_type);
        listPopupWindow = new ListPopupWindow(getActivity());
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Add Assistant");

        ad_dctr_nme_edtx = v.findViewById(R.id.ad_dctr_nme_edtx);
        ad_dctr_mbile_edtx= v.findViewById( R.id.ad_dctr_mbile_edtx);
        ad_dctr_avabletme_edtx = v.findViewById(R.id.ad_dctr_avabletme_edtx);
        ad_dctr_tmetwo_edtx= v.findViewById( R.id.ad_dctr_tmetwo_edtx);
        ad_dctr_asgndctr_edtx = v.findViewById(R.id.ad_dctr_asgndctr_edtx);

        ad_dctr_sbmt_btn = v.findViewById(R.id.ad_dctr_sbmt_btn);

        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

        ad_dctr_avabletme_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);

                                ad_dctr_avabletme_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        ad_dctr_tmetwo_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                month = calendar.get(Calendar.MINUTE);
                //  Am_PM = calendar.get(Calendar.AM_PM);

                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener () {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Do something with the time chosen by the user
                                boolean isPM = (hourOfDay >= 12);

                                ad_dctr_tmetwo_edtx.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });


        ad_dctr_asgndctr_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsignDctrsLstPopup (ad_dctr_asgndctr_edtx);
            }
        });

        ad_dctr_sbmt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
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

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


}
