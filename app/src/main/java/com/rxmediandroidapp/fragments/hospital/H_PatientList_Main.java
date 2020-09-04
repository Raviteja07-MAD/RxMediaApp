package com.rxmediandroidapp.fragments.hospital;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.customfonts.CustomNormalButton;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class H_PatientList_Main extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView h_patlist;
    public static HashMapRecycleviewadapter adapter;
    public static CustomEditText h_patent_date_edtx;
    CustomNormalButton h_today_btn,h_hstry_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_patientlist_main,null,false );
        StoredObjects.page_type="test_suggested";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        h_patlist = v.findViewById( R.id.h_patlist);
        h_patent_date_edtx=v.findViewById(R.id.h_patent_date_edtx);
        h_today_btn=v.findViewById(R.id.h_today_btn);
        h_hstry_btn=v.findViewById(R.id.h_hstry_btn);

        h_patent_date_edtx.setVisibility(View.GONE);
        title_txt.setText( "Patient List" );

        h_hstry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h_patent_date_edtx.setVisibility(View.VISIBLE);
            }
        });
        h_today_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h_patent_date_edtx.setVisibility(View.GONE);
            }
        });

        h_patent_date_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowStartDatePicker(getActivity());
            }
        });
        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });




        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        h_patlist.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(5);
        adapter=new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"h_patientlist_main",h_patlist,R.layout.h_docmain_listitem);
        h_patlist.setAdapter(adapter);

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }


    //datepicker
    public void ShowStartDatePicker(Activity activity) {
        DialogFragment newFragment = new StartDatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }
    public static String seldate="";

    public static class StartDatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePicker = new DatePickerDialog(getActivity(), this, year, month, day);
            datePicker.getDatePicker().setMaxDate(c.getTimeInMillis());

            //Create a new instance of DatePickerDialog and return it
            return datePicker;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            month = month + 1;
            String selecteddate="";
            if(month<10&&day>=10){
                seldate = year+"-0"+month + "-" + day;
                selecteddate=day + "/"  + "0"+month + "/"+ year;
            }else if(month>=10&&day<10){

                seldate = year+"-"+month + "-0" + day;
                selecteddate="0"+day + "/"  + month + "/"+ year;
            }else if(month<10&&day<10){
                seldate = year+"-0"+month + "-0" + day ;
                selecteddate="0"+day + "/"  +"0"+  month + "/"+ year;
            }else{
                seldate = year+"-"+month + "-" + day ;
                selecteddate=day + "/"+  month + "/"+ year;
            }

            h_patent_date_edtx.setText(selecteddate);
        }
    }

}




