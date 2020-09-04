package com.rxmediandroidapp.fragments.hospital;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.fragments.Home;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;


public class H_Prescription_Filter extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    EditText test_date_edtx,seldoc_edtx;
    Button h_submit_btn;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    private ListPopupWindow listPopupWindow;
    String[] doctrslist = {"Dr. Ravi","Dr. Pradeep Reddy","Dr. Abhishek"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_prescription_filter,null,false );
        StoredObjects.page_type="h_prescription_filter";
        StoredObjects.back_type="home";
        listPopupWindow = new ListPopupWindow(getActivity());
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        seldoc_edtx = v.findViewById( R.id. seldoc_edtx);
        h_submit_btn = v.findViewById( R.id. h_submit_btn);
        test_date_edtx = v.findViewById( R.id. test_date_edtx);
        title_txt.setText( "Prescriptions Filter" );

        h_submit_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Doc_TestSuggested() );
            }
        } );

        seldoc_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsignDctrsLstPopup ((CustomEditText) seldoc_edtx);
            }
        });


        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home() );
            }
        } );

        test_date_edtx.setOnClickListener(new View.OnClickListener() {
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
                                test_date_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

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


