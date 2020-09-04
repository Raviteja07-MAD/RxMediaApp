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


public class H_Assistant extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    LinearLayout add_assistant_lay;

    RecyclerView h_assistant_recyler;

    EditText ass_search_edtxt;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.h_assistant,null,false );
        StoredObjects.page_type="h_assistant";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        add_assistant_lay= v.findViewById( R.id. add_assistant_lay);
        h_assistant_recyler = v.findViewById( R.id. h_assistant_recyler);
        ass_search_edtxt = v.findViewById( R.id. ass_search_edtxt);

        title_txt.setText( "Assistant" );


        backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });
        add_assistant_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay(new H_AddAssistant());
            }
        });

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        h_assistant_recyler.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        h_assistant_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"h_assistant",h_assistant_recyler,R.layout.h_assistant_listitem));

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


