package com.rxmediandroidapp.customadapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomRegularTextView;
import com.rxmediandroidapp.fragments.Add_Apntmnt_Three;
import com.rxmediandroidapp.fragments.Ass_PatientList;
import com.rxmediandroidapp.fragments.doctor.Doc_Patient_List;
import com.rxmediandroidapp.fragments.doctor.Doctor_Details;
import com.rxmediandroidapp.fragments.hospital.H_DoctorsList;
import com.rxmediandroidapp.fragments.hospital.H_PatentList;
import com.rxmediandroidapp.fragments.hospital.H_PatientList_Main;
import com.rxmediandroidapp.fragments.hospital.H_Patient_Details;
import com.rxmediandroidapp.fragments.hospital.H_Profile;
import com.rxmediandroidapp.fragments.hospital.H_TotalPrescriptions;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;


class HashmapViewHolder extends RecyclerView.ViewHolder {

    private Activity activity;
    HashMapRecycleviewadapter adapter;

    //p_tst_sugstn
    ImageView p_tst_delete_img;

    //doc_prfle
    LinearLayout d_prf_dctrnme_lay,d_prf_apntmnt_lay;
    ImageView d_prf_up_img,d_prf_down_img;

    //h_patentlst
    LinearLayout h_patnttmngs_lay;

    //ad_apntmnt
    LinearLayout ad_patntlst_lay,ad_patntdtls_lay;
    ImageView ad_patntlst_up_img,ad_patntlst_down_img;

    //add_appointment
    LinearLayout add_appointment_lay,appointment_innerlay;
    CustomButton apnmnt_adapntmnt_btn;
    int count=0;

    CustomRegularTextView docdetails_txt;

    ImageView yellow_circle,red_circle;

    RecyclerView main_innerlist;
    ImageView expand_img,collapse_img;
    TextView h_maintitle_txt;
    LinearLayout mainlist_lay,docdetails_lay,h_docmain_lay;

    //ass_patientlist
    RecyclerView ass_main_innerlist;
    ImageView ass_expand_img,ass_collapse_img;
    TextView ass_maintitle_txt;
    LinearLayout ass_mainlist_lay;


    HashmapViewHolder(View convertView, String type, final Activity activity) {

        super(convertView);
        this.activity = activity;

        if (type.equalsIgnoreCase("home")) {

        }
        else  if (type.equalsIgnoreCase("h_doctorslist")) {
            main_innerlist = convertView.findViewById(R.id.main_innerlist);
            expand_img = convertView.findViewById(R.id.expand_img);
            collapse_img = convertView.findViewById(R.id.collapse_img);
            h_maintitle_txt = convertView.findViewById(R.id.h_maintitle_txt);
            mainlist_lay=convertView.findViewById(R.id.mainlist_lay);

        }
        else  if (type.equalsIgnoreCase("h_presclist")) {
            main_innerlist = convertView.findViewById(R.id.main_innerlist);
            expand_img = convertView.findViewById(R.id.expand_img);
            collapse_img = convertView.findViewById(R.id.collapse_img);
            h_maintitle_txt = convertView.findViewById(R.id.h_maintitle_txt);
            mainlist_lay=convertView.findViewById(R.id.mainlist_lay);

        }
        else  if (type.equalsIgnoreCase("ass_patientlist")) {
            ass_main_innerlist = convertView.findViewById(R.id.ass_main_innerlist);
            ass_expand_img = convertView.findViewById(R.id.ass_expand_img);
            ass_collapse_img = convertView.findViewById(R.id.ass_collapse_img);
            ass_maintitle_txt = convertView.findViewById(R.id.ass_maintitle_txt);
            ass_mainlist_lay=convertView.findViewById(R.id.ass_mainlist_lay);

        }

       else  if (type.equalsIgnoreCase("h_patientlist_main")) {
            main_innerlist = convertView.findViewById(R.id.main_innerlist);
            expand_img = convertView.findViewById(R.id.expand_img);
            collapse_img = convertView.findViewById(R.id.collapse_img);
            h_maintitle_txt = convertView.findViewById(R.id.h_maintitle_txt);
            mainlist_lay=convertView.findViewById(R.id.mainlist_lay);

        }
        else if (type.equalsIgnoreCase("p_tst_sugstn")) {

            p_tst_delete_img = convertView.findViewById(R.id.p_tst_delete_img);
        }

        else if (type.equalsIgnoreCase("h_patentlst")) {

            h_patnttmngs_lay = convertView.findViewById(R.id.h_patnttmngs_lay);
        }


        else if (type.equalsIgnoreCase("ad_apntmnt")) {

            ad_patntlst_lay = convertView.findViewById(R.id.ad_patntlst_lay);
            ad_patntdtls_lay = convertView.findViewById(R.id.ad_patntdtls_lay);
            ad_patntlst_up_img = convertView.findViewById(R.id.ad_patntlst_up_img);
            ad_patntlst_down_img = convertView.findViewById(R.id.ad_patntlst_down_img);
        }

        else if (type.equalsIgnoreCase("doc_prfle")) {

            d_prf_dctrnme_lay = convertView.findViewById(R.id.d_prf_dctrnme_lay);
            d_prf_apntmnt_lay = convertView.findViewById(R.id.d_prf_apntmnt_lay);
            d_prf_up_img = convertView.findViewById(R.id.d_prf_up_img);
            d_prf_down_img = convertView.findViewById(R.id.d_prf_down_img);
        }
        else if (type.equalsIgnoreCase("addapointment")) {

            add_appointment_lay = convertView.findViewById(R.id.add_appointment_lay);
            appointment_innerlay = convertView.findViewById(R.id.appointment_innerlay);
            apnmnt_adapntmnt_btn = convertView.findViewById(R.id.apnmnt_adapntmnt_btn);
        }else if(type.equalsIgnoreCase("doc_details")){
            h_docmain_lay=convertView.findViewById(R.id.h_docmain_lay);
            docdetails_txt=convertView.findViewById(R.id.docdetails_txt);
        }
        else if (type.equalsIgnoreCase("medication")) {

            yellow_circle = convertView.findViewById(R.id.yellow_circle);
            red_circle = convertView.findViewById(R.id.red_circle);
        }

       else if (type.equalsIgnoreCase("h_docsinnerlist")) {

            docdetails_lay = convertView.findViewById(R.id.docdetails_lay);
        }


    }

    void assign_data(final ArrayList<HashMap<String, String>> datalist, final int position, String formtype) {


        if (formtype.equalsIgnoreCase("h_docsinnerlist")) {

            docdetails_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   fragmentcalling(new Doctor_Details());
                }
            });
        }

       else  if (formtype.equalsIgnoreCase("h_presclist")) {
            if(datalist.get(position).get("is_viewed").equals("Yes")){
                main_innerlist.setVisibility(View.VISIBLE);
                expand_img.setVisibility(View.GONE);
                collapse_img.setVisibility(View.VISIBLE);
            }else{
                main_innerlist.setVisibility(View.GONE);
                expand_img.setVisibility(View.VISIBLE);
                collapse_img.setVisibility(View.GONE);
            }

            StoredObjects.subhashmaplist(3);
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
            main_innerlist.setLayoutManager(linearLayoutManager);

            main_innerlist.setAdapter(new HashMapRecycleviewadapter(activity, StoredObjects.subdummy_list,"h_presinnerlist",main_innerlist,R.layout.h_pressub_listitem));

            mainlist_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(datalist.get(position).get("is_viewed").equals("Yes")){
                        updatedata(datalist,datalist.get(position),position,"No","h_presclist");
                    }else{
                        updatedata(datalist,datalist.get(position),position,"Yes","h_presclist");
                    }
                }
            });
        }
        else  if (formtype.equalsIgnoreCase("ass_patientlist")) {
            if(datalist.get(position).get("is_viewed").equals("Yes")){
                ass_main_innerlist.setVisibility(View.VISIBLE);
                ass_expand_img.setVisibility(View.GONE);
                ass_collapse_img.setVisibility(View.VISIBLE);
            }else{
                ass_main_innerlist.setVisibility(View.GONE);
                ass_expand_img.setVisibility(View.VISIBLE);
                ass_collapse_img.setVisibility(View.GONE);
            }

            StoredObjects.subhashmaplist(3);
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
            ass_main_innerlist.setLayoutManager(linearLayoutManager);

            ass_main_innerlist.setAdapter(new HashMapRecycleviewadapter(activity, StoredObjects.subdummy_list,"ass_inner_patientlist",ass_main_innerlist,R.layout.ass_patientsub_listitem));

            ass_mainlist_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(datalist.get(position).get("is_viewed").equals("Yes")){
                        updatedata(datalist,datalist.get(position),position,"No","ass_patientlist");
                    }else{
                        updatedata(datalist,datalist.get(position),position,"Yes","ass_patientlist");
                    }
                }
            });
        }

        else  if (formtype.equalsIgnoreCase("h_patientlist_main")) {
            if(datalist.get(position).get("is_viewed").equals("Yes")){
                main_innerlist.setVisibility(View.VISIBLE);
                expand_img.setVisibility(View.GONE);
                collapse_img.setVisibility(View.VISIBLE);
            }else{
                main_innerlist.setVisibility(View.GONE);
                expand_img.setVisibility(View.VISIBLE);
                collapse_img.setVisibility(View.GONE);
            }

            StoredObjects.subhashmaplist(3);
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
            main_innerlist.setLayoutManager(linearLayoutManager);

            main_innerlist.setAdapter(new HashMapRecycleviewadapter(activity, StoredObjects.subdummy_list,"h_patientinnerlist",main_innerlist,R.layout.h_patsub_listitem));

            mainlist_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(datalist.get(position).get("is_viewed").equals("Yes")){
                        updatedata(datalist,datalist.get(position),position,"No","h_patientlist_main");
                    }else{
                        updatedata(datalist,datalist.get(position),position,"Yes","h_patientlist_main");
                    }
                }
            });
        }
        else if (formtype.equalsIgnoreCase("h_doctorslist")) {
            if(datalist.get(position).get("is_viewed").equals("Yes")){
                main_innerlist.setVisibility(View.VISIBLE);
                expand_img.setVisibility(View.GONE);
                collapse_img.setVisibility(View.VISIBLE);
            }else{
                main_innerlist.setVisibility(View.GONE);
                expand_img.setVisibility(View.VISIBLE);
                collapse_img.setVisibility(View.GONE);
            }

            StoredObjects.subhashmaplist(3);
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
            main_innerlist.setLayoutManager(linearLayoutManager);

            main_innerlist.setAdapter(new HashMapRecycleviewadapter(activity, StoredObjects.subdummy_list,"h_docsinnerlist",main_innerlist,R.layout.h_docsub_listitem));

            mainlist_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(datalist.get(position).get("is_viewed").equals("Yes")){
                        updatedata(datalist,datalist.get(position),position,"No","h_doctorslist");
                    }else{
                        updatedata(datalist,datalist.get(position),position,"Yes","h_doctorslist");
                    }
                }
            });
        }
       else if (formtype.equalsIgnoreCase("doc_details")) {
            docdetails_txt.setText(datalist.get(position).get("name"));

            h_docmain_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position==0){
                        fragmentcalling(new H_PatentList());
                    }else  if(position==1){
                        fragmentcalling(new H_PatentList());
                    }else{
                        fragmentcalling(new H_PatentList());
                    }
                }
            });

        }
        else if (formtype.equalsIgnoreCase("medication")) {
            if (position==1){
                red_circle.setVisibility(View.VISIBLE);
                yellow_circle.setVisibility(View.VISIBLE);
            }
            else {
                red_circle.setVisibility(View.GONE);
                yellow_circle.setVisibility(View.GONE);
            }
        }
        else if (formtype.equalsIgnoreCase("addapointment")) {

            apnmnt_adapntmnt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position == 1) {

                        if (count == 0) {
                            count++;
                            appointment_innerlay.setVisibility(View.VISIBLE);
                        } else {
                            if (count == 1)
                                count = 0;
                            appointment_innerlay.setVisibility(View.GONE);
                        }
                    } else {


                    }


                }

            });
        }

        else if (formtype.equalsIgnoreCase("p_tst_sugstn")) {

            p_tst_delete_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new H_Profile());

                }
            });
        }

        else if (formtype.equalsIgnoreCase("h_patentlst")) {

            h_patnttmngs_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new H_Patient_Details());

                }
            });
        }


        else if (formtype.equalsIgnoreCase("ad_apntmnt")) {

            if(position==1){
                ad_patntlst_lay.setBackgroundResource(R.drawable.patient_bg_clr);
            }
            ad_patntlst_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   /* if(viewMore.getText().equals("View More")) {
                        ad_patntdtls_lay.setVisibility(View.VISIBLE);
                        ad_patntlst_up_img.setVisibility(View.GONE);
                        ad_patntlst_down_img.setVisibility(View.VISIBLE);
                    }
                    else {
                        ad_patntdtls_lay.setVisibility(View.GONE);
                        ad_patntlst_up_img.setVisibility(View.VISIBLE);
                        ad_patntlst_down_img.setVisibility(View.GONE);

                    }*/
                }
            });
        }

        else if (formtype.equalsIgnoreCase("doc_prfle")) {

            d_prf_dctrnme_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   /* if(viewMore.getText().equals("View More")) {
                        d_prf_apntmnt_lay.setVisibility(View.VISIBLE);
                        d_prf_up_img.setVisibility(View.GONE);
                        d_prf_down_img.setVisibility(View.VISIBLE);
                    }
                    else {
                        d_prf_apntmnt_lay.setVisibility(View.GONE);
                        d_prf_up_img.setVisibility(View.VISIBLE);
                        d_prf_down_img.setVisibility(View.GONE);

                    }*/
                }
            });
        }
    }

    public void fragmentcalling(Fragment fragment) {
        FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }
    //update data
    public void updatedata(final ArrayList<HashMap<String, String>> list, HashMap<String, String> HashMapdata, int postion, String val, String type) {

        int i = list.indexOf(HashMapdata);

        if (i == -1) {
            throw new IndexOutOfBoundsException();
        }

        if(type.equalsIgnoreCase("h_doctorslist")){

            HashMap<String, String> dumpData_update = new HashMap<String, String>();
            dumpData_update.put("is_viewed", val);
            dumpData_update.put("name", list.get(postion).get("name"));

            list.remove(HashMapdata);
            list.add(postion,dumpData_update);
            H_DoctorsList.adapter.notifyDataSetChanged();

        }

        if(type.equalsIgnoreCase("h_presclist")){

            HashMap<String, String> dumpData_update = new HashMap<String, String>();
            dumpData_update.put("is_viewed", val);
            dumpData_update.put("name", list.get(postion).get("name"));

            list.remove(HashMapdata);
            list.add(postion,dumpData_update);
            H_TotalPrescriptions.adapter.notifyDataSetChanged();

        }
        if(type.equalsIgnoreCase("ass_patientlist")){

            HashMap<String, String> dumpData_update = new HashMap<String, String>();
            dumpData_update.put("is_viewed", val);
            dumpData_update.put("name", list.get(postion).get("name"));

            list.remove(HashMapdata);
            list.add(postion,dumpData_update);
            Ass_PatientList.adapter.notifyDataSetChanged();

        }


        if(type.equalsIgnoreCase("h_patientlist_main")){

            HashMap<String, String> dumpData_update = new HashMap<String, String>();
            dumpData_update.put("is_viewed", val);
            dumpData_update.put("name", list.get(postion).get("name"));

            list.remove(HashMapdata);
            list.add(postion,dumpData_update);
            H_PatientList_Main.adapter.notifyDataSetChanged();

        }
    }

}
