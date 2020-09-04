package com.rxmediandroidapp.fragments.hospital;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;


public class H_Test_Suggested extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView testsuggested_recycle;
    EditText test_date_edtx;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.test_suggested,null,false );
        StoredObjects.page_type="test_suggested";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        testsuggested_recycle = v.findViewById( R.id. testsuggested_recycle);
        test_date_edtx = v.findViewById( R.id. test_date_edtx);

        title_txt.setText( "Test Suggested" );


        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

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
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });



        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        testsuggested_recycle.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(5);
        testsuggested_recycle.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"testsuggested",testsuggested_recycle,R.layout.test_suggested_listitem));

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


