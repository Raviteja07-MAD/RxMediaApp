package com.rxmediandroidapp.fragments.hospital;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.Calendar;

public class H_DoctorsList extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    RecyclerView h_doclist;
   public static HashMapRecycleviewadapter adapter;
   LinearLayout adddoc_lay;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_doctorslist,null,false );
        StoredObjects.page_type="test_suggested";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        h_doclist = v.findViewById( R.id.h_doclist);
        adddoc_lay=v.findViewById(R.id.adddoc_lay);

        title_txt.setText( "Doctors" );


        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });

        adddoc_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay(new H_AddDoctor());

            }
        });


        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        h_doclist.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(5);
        adapter=new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"h_doctorslist",h_doclist,R.layout.h_docmain_listitem);
        h_doclist.setAdapter(adapter);

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}



