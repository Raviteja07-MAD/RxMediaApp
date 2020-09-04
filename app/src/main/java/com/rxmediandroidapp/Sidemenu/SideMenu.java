package com.rxmediandroidapp.Sidemenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.activities.SignIn;
import com.rxmediandroidapp.customadapter.HashMapRecycleviewadapter;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.fragments.Add_Diagnosis;
import com.rxmediandroidapp.fragments.Ass_PatientList;
import com.rxmediandroidapp.fragments.Block_Doctor;
import com.rxmediandroidapp.fragments.Diagnosis_Reports_Details;
import com.rxmediandroidapp.fragments.hospital.H_Assistant;
import com.rxmediandroidapp.fragments.hospital.H_Prescription_Details;
import com.rxmediandroidapp.fragments.hospital.H_Test_Suggested;
import com.rxmediandroidapp.fragments.Home;
import com.rxmediandroidapp.fragments.Print_Formate;
import com.rxmediandroidapp.fragments.doctor.Doc_Patient_Details;
import com.rxmediandroidapp.fragments.doctor.Doc_Patient_List;
import com.rxmediandroidapp.fragments.doctor.Doc_Profile;
import com.rxmediandroidapp.fragments.hospital.H_DoctorsList;
import com.rxmediandroidapp.fragments.hospital.H_PatientList_Main;
import com.rxmediandroidapp.fragments.hospital.H_Patient_Details;
import com.rxmediandroidapp.fragments.hospital.H_Profile;
import com.rxmediandroidapp.fragments.hospital.H_TotalPrescriptions;
import com.rxmediandroidapp.fragments.patient.Members_Details;
import com.rxmediandroidapp.fragments.patient.P_Test_Sugestions;
import com.rxmediandroidapp.storedobjects.StoredObjects;

import java.util.ArrayList;


public class SideMenu extends AppCompatActivity {

    //actionbarlayout
    public static ImageView custm_menu_img, custm_backbtn_img, custm_search_img;
    public static TextView custm_title_txt;
    public static LinearLayout actionbar_lay, bottom_layout, custom_new_actionbar_lay,top_lay;
    ImageView sidemnu_cancel_img;
    public static TextView header_name;
    public static ImageView header_circular_img;

    public static DrawerLayout mDrawerLayout;
    public static ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    String[] navMenuTitles;
    private TypedArray navMenuIconsleft;
    private ArrayList<NavDrawerItem> navDrawerItems;
    public static NavDrawerListAdapter adapter;
    public static int drawablecount = 0;
    public static LinearLayout list_slidermenu_lay;

    public static LinearLayout btm_log_catch_lay, btmaddpost_lay, btm_map_lay, btm_notifications_lay, btm_profile_lay;
    public static ImageView btm_log_catch_img, btm_addpost_img, btm_map_img, btm_notifications_img, btm_profile_img;

    public static LinearLayout btm_notifications_lay_bg;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenu);
        initialization();
    }

    public void initialization() {

        list_slidermenu_lay = (LinearLayout) findViewById(R.id.list_slidermenu_lay);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        if( StoredObjects.UserType.equalsIgnoreCase("Doctor")){

            navMenuTitles = getResources().getStringArray(R.array.doctor_list);
        }else if(StoredObjects.UserType.equalsIgnoreCase("Patient")){
            navMenuTitles = getResources().getStringArray(R.array.patient_list);
        }else if(StoredObjects.UserType.equalsIgnoreCase("Hospital")){
            navMenuTitles = getResources().getStringArray(R.array.hospital_list);
        }


        for (int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i]));

        }


        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(SideMenu.this, navDrawerItems);
        mDrawerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        top_lay  = (LinearLayout) findViewById(R.id.top_lay);
         custm_menu_img = (ImageView)findViewById(R.id.custm_menu_img);
         custm_backbtn_img = (ImageView)findViewById(R.id.custm_backbtn_img);
         custm_title_txt = (TextView) findViewById(R.id.custm_title_txt);
         custm_search_img = (ImageView)findViewById( R.id.custm_search_img );
        actionbar_lay = (LinearLayout) findViewById(R.id.actionbar_lay);
        bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);

        sidemnu_cancel_img = (ImageView) findViewById(R.id.sidemnu_cancel_img);
        header_name = findViewById(R.id.header_name);
        header_circular_img =  findViewById(R.id.header_circular_img);


        btm_log_catch_lay = (LinearLayout) findViewById(R.id.btm_log_catch_lay);
        btm_map_lay = (LinearLayout) findViewById(R.id.btm_map_lay);
        btm_notifications_lay = (LinearLayout) findViewById(R.id.btm_notifications_lay);
        btm_profile_lay = (LinearLayout) findViewById(R.id.btm_profile_lay);

        btm_log_catch_img = (ImageView) findViewById(R.id.btm_log_catch_img);
        btm_map_img = (ImageView) findViewById(R.id.btm_map_img);
        btm_notifications_img = (ImageView) findViewById(R.id.btm_notifications_img);
        btm_profile_img = (ImageView) findViewById(R.id.btm_profile_img);

        btm_notifications_lay_bg = findViewById(R.id.btm_notifications_lay_bg);

        custm_title_txt.setText("Home");
        if( StoredObjects.UserType.equalsIgnoreCase("Doctor")){

            header_name.setText("Doctor Name");
        }else if(StoredObjects.UserType.equalsIgnoreCase("Patient")){
            header_name.setText("Patient Name");
        }else if(StoredObjects.UserType.equalsIgnoreCase("Hospital")){
            header_name.setText("Hospital Name");
        }


        sidemnu_cancel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.closeDrawer(list_slidermenu_lay);
            }
        });


        custm_menu_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawablecount++;
                if (drawablecount == 1) {
                    mDrawerLayout.openDrawer(list_slidermenu_lay);
                } else {
                    drawablecount = 0;
                    mDrawerLayout.closeDrawer(list_slidermenu_lay);
                }
            }

        });


        custm_backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbuttonclickevents();
            }
        });

        buttonchangemethod(SideMenu.this, btm_log_catch_lay, btm_log_catch_img, "0");
        fragmentcallinglay(new Home());

        buttonchangelaymethod(SideMenu.this, btm_log_catch_lay, btm_log_catch_img, "0");
        buttonchangelaymethod(SideMenu.this, btm_map_lay, btm_map_img, "1");
        buttonchangelaymethod(SideMenu.this, btm_notifications_lay, btm_notifications_img, "2");
        buttonchangelaymethod(SideMenu.this, btm_profile_lay, btm_profile_img, "3");
    }

    public void buttonchangelaymethod(final Activity activity, final LinearLayout layout1, final ImageView image,  final String type) {

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonchangemethod(activity, layout1, image,  type);
/*
                if (type.equalsIgnoreCase("0")) {
                    fragmentcallinglay(new Home());
                } else if (type.equalsIgnoreCase("1")) {
                    fragmentcallinglay(new AddPatient());
                } else if (type.equalsIgnoreCase("2")) {
                    fragmentcallinglay(new Print_Formate());
                } else if (type.equalsIgnoreCase("3")) {
                fragmentcallinglay(new Diagnosis_Reports_Details());
                }*/
            }
        });
    }

    public static void buttonchangemethod(Activity activity, LinearLayout layout1, ImageView image,   String type) {

        btm_log_catch_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_map_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_notifications_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_profile_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));

        btm_log_catch_img.setColorFilter(ContextCompat.getColor(activity, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_map_img.setColorFilter(ContextCompat.getColor(activity, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_notifications_img.setColorFilter(ContextCompat.getColor(activity, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_profile_img.setColorFilter(ContextCompat.getColor(activity, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);

        image.setColorFilter(ContextCompat.getColor(activity, R.color.theme_color), android.graphics.PorterDuff.Mode.SRC_IN);

    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
            displayView(position);
            adapter.notifyDataSetChanged();
        }
    }

    public void displayView(int position) {


        Fragment fragment = null;
        StoredObjects.listcount = position;
        if( StoredObjects.UserType.equalsIgnoreCase("Doctor")){
            switch (position) {

                case 0:
                    assessmentpopup (SideMenu.this);
                    break;
                case 1:
                    Diagnosepopup(SideMenu.this);
                    break;
                case 2:
                    medicationpopup (SideMenu.this);
                    break;
                case 3:
                    fragment  = new Doc_Patient_Details();
                    break;

                case 4:
                    fragment  = new Doc_Patient_List();
                    break;

                case 5:
                    fragment  = new Print_Formate();
                    break;
                case 6:
                    fragment  = new Doc_Profile();
                    break;
                case 7:
                    fragment  = new Doc_Patient_Details();
                    break;


                case 8:
                    Logoutpopup(SideMenu.this,"1");
                    break;

            }
        }else if(StoredObjects.UserType.equalsIgnoreCase("Patient")){
            switch (position) {


                case 0:
                    fragment  = new Diagnosis_Reports_Details();
                    break;
                case 1:
                    fragment  = new Block_Doctor();
                    break;
                case 2:
                    fragment  = new Add_Diagnosis();
                    break;
                case 3:
                    fragment  = new Members_Details();
                    break;

                case 4:
                    fragment  = new P_Test_Sugestions();
                    break;

                case 5:
                    physicalexampopup (SideMenu.this);
                    break;
                case 6:
                    //fragment  = new Doc_Patient_Details();
                    break;
                case 7:

                    Logoutpopup(SideMenu.this,"1");
                    break;

            }
        }else if(StoredObjects.UserType.equalsIgnoreCase("Hospital")){
            switch (position) {


                case 0:
                    fragment  = new H_Prescription_Details();
                    break;
                case 1:
                    fragment  = new Ass_PatientList();
                    break;
                case 2:
                    fragment  = new H_Assistant();
                    break;
                case 3:
                    fragment  = new H_PatientList_Main();
                    //fragment  = new H_Prescription_Details();
                    break;

                case 4:
                    fragment  = new H_TotalPrescriptions();
                    //fragment  = new H_Patient_Details();
                    break;

                case 5:
                    fragment  = new H_Test_Suggested();
                    break;
                case 6:
                    fragment  = new H_Profile();
                    break;
                case 7:

                    Logoutpopup(SideMenu.this,"1");
                    break;

            }
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            mDrawerLayout.closeDrawer(list_slidermenu_lay);

        } else {
            // error in creating fragment
            Log.e("Sidemenu", "Error in creating fragment");
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    public void backbuttonclickevents() {


        if( StoredObjects.back_type.equalsIgnoreCase( "home" )) {
            checkbackclick();
        }
    }

    public void checkbackclick () {

            if (doubleBackToExitPressedOnce) {
                // super.onBackPressed();
                Logoutpopup(SideMenu.this,"0");
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 3000);
        }

        public void DisplayAlertDialog (String string){

            AlertDialog.Builder builder = new AlertDialog.Builder(SideMenu.this);

            builder.setMessage(string)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            minimizeApp();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        public void minimizeApp () {
            finish();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

        public void onBackPressed () {
             backbuttonclickevents();
        }

        public void fragmentcallinglay (Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
        }

        public static void updatemenu (String pagetype){

        if (pagetype.equalsIgnoreCase("home")) {
            top_lay.setVisibility( View.VISIBLE );
            } else {
            top_lay.setVisibility( View.GONE );
            }

        }

        private void Logoutpopup(final Activity activity, final String type) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logooutpopup );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        Button ok_btn = (Button)dialog.findViewById(R.id.ok_btn);
        Button cancel_btn = (Button)dialog.findViewById(R.id.cancel_btn);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        TextView logout_txt = (TextView)dialog.findViewById( R.id.logout_txt );
        TextView exit_txt = (TextView)dialog.findViewById( R.id.exit_txt );

        if (type.equals( "1" )){
            logout_txt.setVisibility( View.VISIBLE );
        }
        else {
            exit_txt.setVisibility( View.VISIBLE );
            logout_txt.setVisibility( View.GONE );
        }

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });



            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type.equals( "1" )) {
                        //database.UpdateUserId( "0" );
                       // database.UpdateUsertype( "0" );
                        activity.finish();
                        Intent intent = new Intent( activity, SignIn.class );
                        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        activity.startActivity( intent );
                    } else {
                        minimizeApp();
                    }
                }
            });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void Diagnosepopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diagnose_suggestion );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        RecyclerView diagnose_suggest_recyclerview = (RecyclerView)dialog.findViewById(R.id.diagnose_suggest_recyclerview);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        diagnose_suggest_recyclerview.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        diagnose_suggest_recyclerview.setAdapter(new HashMapRecycleviewadapter(activity,StoredObjects.dummy_list,"diagnose_suggest",diagnose_suggest_recyclerview,R.layout.diagnose_suggestion_listitems));





        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void assessmentpopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.assessment_and_plan );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);




        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void physicalexampopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_physical_examination );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        RecyclerView add_physical_recycle = (RecyclerView)dialog.findViewById(R.id.add_physical_recycle);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        add_physical_recycle.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        add_physical_recycle.setAdapter(new HashMapRecycleviewadapter(activity,StoredObjects.dummy_list,"add_physical",add_physical_recycle,R.layout.add_physical_examination_listitems));



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void medicationpopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doc_medication );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);

        // CustomEditText tablets_dropdn=dialog.findViewById(R.id.tablets_dropdn);



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void medicationone_popup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doc_medication_one);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        RecyclerView medication_recyclerview = (RecyclerView)dialog.findViewById(R.id.medication_recyclerview);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        medication_recyclerview.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        medication_recyclerview.setAdapter(new HashMapRecycleviewadapter(activity,StoredObjects.dummy_list,"medication",medication_recyclerview,R.layout.doc_medication_one_listitem));



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

    /*private void Diagnosepopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diagnose_suggestion );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);




        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void assessmentpopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.assessment_and_plan );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);




        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void physicalexampopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_physical_examination );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void medicationpopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doc_medication );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);

       // CustomEditText tablets_dropdn=dialog.findViewById(R.id.tablets_dropdn);



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void medicationone_popup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doc_medication_one);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        CustomButton search_button =(CustomButton) dialog.findViewById(R.id.search_button);
        RecyclerView medication_recyclerview = (RecyclerView)dialog.findViewById(R.id.medication_recyclerview);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        medication_recyclerview.setLayoutManager(linearLayoutManager);

        StoredObjects.hashmaplist(2);
        medication_recyclerview.setAdapter(new HashMapRecycleviewadapter(activity,StoredObjects.dummy_list,"medication",medication_recyclerview,R.layout.doc_medication_one_listitem));



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }*/




