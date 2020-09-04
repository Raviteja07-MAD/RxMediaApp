package com.rxmediandroidapp.fragments.hospital;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomEditText;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class H_PatentList extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    CustomEditText h_patent_srch_edtx,h_patent_date_edtx;
    static RecyclerView h_patent_recyler;
    public static HashMapRecycleviewadapter adapter;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_patents_lst,null,false );
        StoredObjects.page_type="h_patentlst";
        StoredObjects.back_type="h_patentlst";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt = v.findViewById(R.id.title_txt);
        h_patent_srch_edtx = v.findViewById(R.id.h_patent_srch_edtx);
        h_patent_date_edtx = v.findViewById(R.id.h_patent_date_edtx);
        h_patent_recyler = v.findViewById(R.id.h_patent_recyler);
        title_txt.setText("Patient List");

        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

        h_patent_date_edtx.setOnClickListener(new View.OnClickListener() {
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
                                h_patent_date_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        h_patent_recyler.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(3);
        adapter = new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"h_patentlst",h_patent_recyler,R.layout.h_patents_lstitem);
        h_patent_recyler.setAdapter(adapter);

    }

    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ().replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }


}
