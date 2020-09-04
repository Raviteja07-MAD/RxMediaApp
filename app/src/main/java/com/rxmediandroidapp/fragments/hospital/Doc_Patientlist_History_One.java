package com.rxmediandroidapp.fragments.hospital;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Doc_Patientlist_History_One extends Fragment {

    ImageView backbtn_img;
    TextView title_txt;
    LinearLayout add_assistant_lay;

    RecyclerView patientlist_history_recyler;


    EditText ass_search_edtxt;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.doc_patientlist_history_one,null,false );
        StoredObjects.page_type="patientlist_history_one";
        StoredObjects.back_type="home";
        SideMenu.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById( R.id. backbtn_img);
        title_txt= v.findViewById( R.id. title_txt);
        add_assistant_lay= v.findViewById( R.id. add_assistant_lay);
        patientlist_history_recyler = v.findViewById( R.id. patientlist_history_recyler);
        ass_search_edtxt = v.findViewById( R.id. ass_search_edtxt);

        title_txt.setText( "Physical Suggestion" );


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
        patientlist_history_recyler.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        patientlist_history_recyler.setAdapter(new HashMapRecycleviewadapter(getActivity(),StoredObjects.dummy_list,"patientlist_history",patientlist_history_recyler,R.layout.doc_patientlist_history_listitem));

    }


    public void fragmentcallinglay(Fragment fragment) {
        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }




}


