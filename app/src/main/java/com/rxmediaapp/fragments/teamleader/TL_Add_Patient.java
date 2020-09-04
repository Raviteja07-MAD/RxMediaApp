package com.rxmediaapp.fragments.teamleader;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.rxmediaapp.R;
import com.rxmediaapp.Sidemenu.SideMenu;
import com.rxmediaapp.customfonts.CustomButton;
import com.rxmediaapp.customfonts.CustomEditText;
import com.rxmediaapp.fragments.assistant.Add_Appointment_One;
import com.rxmediaapp.serviceparsing.APIInterface;
import com.rxmediaapp.serviceparsing.CustomProgressbar;
import com.rxmediaapp.serviceparsing.InterNetChecker;
import com.rxmediaapp.serviceparsing.JsonParsing;
import com.rxmediaapp.serviceparsing.RetrofitInstance;
import com.rxmediaapp.storedobjects.StoredObjects;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TL_Add_Patient extends Fragment {

    CustomEditText apnmnt_nme_edtx,apnmnt_email_edtx,apnmnt_mbile_edtx,apnmnt_adhar_edtx,
            apnmnt_dob_edtx,apnmnt_gender_edtx,apnmnt_bldgrup_edtx,apnmnt_cnsltdctr_edtx,apnmnt_password_edtx,apnmnt_conf_pass_edtx;

    ImageView backbtn_img;
    TextView title_txt;
    CustomButton apnmnt_adapntmnt_btn;


    String[] genderlist = {"Male","Female"};

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    TextView pat_notfound_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.add_apntmnt_one,null,false );
        StoredObjects.page_type="add_apntmnt";

        SideMenu.updatemenu(StoredObjects.page_type);

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        backbtn_img = v.findViewById(R.id.backbtn_img);
        title_txt= v.findViewById( R.id.title_txt);
        title_txt.setText( "Add Patient" );

        apnmnt_nme_edtx = v.findViewById(R.id.apnmnt_nme_edtx);
        apnmnt_email_edtx= v.findViewById( R.id.apnmnt_email_edtx);
        apnmnt_mbile_edtx = v.findViewById(R.id.apnmnt_mbile_edtx);
        apnmnt_adhar_edtx= v.findViewById( R.id.apnmnt_adhar_edtx);
        apnmnt_dob_edtx = v.findViewById(R.id.apnmnt_dob_edtx);
        apnmnt_gender_edtx= v.findViewById( R.id.apnmnt_gender_edtx);
        apnmnt_bldgrup_edtx = v.findViewById(R.id.apnmnt_bldgrup_edtx);
        apnmnt_password_edtx = v.findViewById(R.id.apnmnt_password_edtx);
        apnmnt_conf_pass_edtx = v.findViewById(R.id.apnmnt_confirm_pass_edtx);
        apnmnt_cnsltdctr_edtx= v.findViewById( R.id.apnmnt_cnsltdctr_edtx);
        pat_notfound_txt=v.findViewById(R.id.pat_notfound_txt);
        apnmnt_adapntmnt_btn = v.findViewById(R.id.apnmnt_adapntmnt_btn);
        apnmnt_adapntmnt_btn.setText("Submit");

        apnmnt_cnsltdctr_edtx.setImeOptions(EditorInfo.IME_ACTION_DONE);
        apnmnt_cnsltdctr_edtx.setRawInputType(InputType.TYPE_CLASS_TEXT);

        apnmnt_conf_pass_edtx.setVisibility(View.GONE);
        pat_notfound_txt.setVisibility(View.GONE);
        apnmnt_cnsltdctr_edtx.setVisibility(View.GONE);



        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }

            }
        } );


        apnmnt_dob_edtx.setOnClickListener(new View.OnClickListener() {
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
                                apnmnt_dob_edtx.setText(StoredObjects.GetSelectedDate(day,month,year));
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        apnmnt_gender_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenderListPopup (apnmnt_gender_edtx,getActivity());
            }
        });

        apnmnt_bldgrup_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BloodgroupPopup(apnmnt_bldgrup_edtx,getActivity());
            }
        });

        apnmnt_adapntmnt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_str = apnmnt_nme_edtx.getText().toString().trim();
                String email_str = apnmnt_email_edtx.getText().toString().trim();
                String dob_str = apnmnt_dob_edtx.getText().toString().trim();
                String gender_str = apnmnt_gender_edtx.getText().toString().trim();
                String aadhar_str = apnmnt_adhar_edtx.getText().toString().trim();
                String blood_grp_str = apnmnt_bldgrup_edtx.getText().toString().trim();
                String phone_str = apnmnt_mbile_edtx.getText().toString().trim();
                String password_str = apnmnt_password_edtx.getText().toString().trim();

                if(StoredObjects.inputValidation(apnmnt_nme_edtx,getString(R.string.dr_name),getActivity()))
                {
                    if(StoredObjects.Emailvalidation(email_str,getString(R.string.enter_valid_email),getActivity()))
                    {
                        if(StoredObjects.inputValidation(apnmnt_mbile_edtx,getString(R.string.enter_mobile_validation),getActivity())) {
                            if (StoredObjects.isValidMobile(phone_str)) {
                                if (StoredObjects.inputValidation(apnmnt_dob_edtx, getString(R.string.dob_validation), getActivity())) {
                                    if (StoredObjects.inputValidation(apnmnt_gender_edtx, getString(R.string.gender_validate), getActivity())) {
                                        if (StoredObjects.inputValidation(apnmnt_bldgrup_edtx, getString(R.string.blood_validation), getActivity())) {

                                            if (StoredObjects.inputValidation(apnmnt_password_edtx, getString(R.string.enter_pass_validation), getActivity())) {

                                                if (InterNetChecker.isNetworkAvailable(getActivity())) {
                                                    addPatientService(getActivity(), name_str, email_str, dob_str, gender_str, aadhar_str, blood_grp_str, phone_str, password_str);
                                                } else {
                                                    StoredObjects.ToastMethod(getString(R.string.nointernet), getActivity());
                                                }
                                            }


                                        }
                                    }

                                }


                            } else {
                                StoredObjects.ToastMethod(getString(R.string.enter_valid_mobile), getActivity());
                            }
                        }
                    }
                }

            }
        });

        if (InterNetChecker.isNetworkAvailable(getActivity())) {
            getBloodGroup(getActivity());

        } else {
            StoredObjects.ToastMethod(getActivity().getResources().getString(R.string.nointernet), getActivity());
        }
    }
    String blood_id="";
    private void BloodgroupPopup(final CustomEditText prfilenme,Activity activity) {
        final ListPopupWindow dropdownpopup = new ListPopupWindow(activity);
        dropdownpopup.setAdapter(new ArrayAdapter<>(activity, R.layout.drpdwn_lay, bloodgroupnames));
        dropdownpopup.setAnchorView(prfilenme);
        dropdownpopup.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        dropdownpopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(bloodgroupnames.get(position));
                blood_id=bloodgroup_list.get(position).get("id");
                dropdownpopup.dismiss();

            }
        });

        dropdownpopup.show();
    }
    ArrayList<String> bloodgroupnames = new ArrayList<>();
    ArrayList<HashMap<String, String>> bloodgroup_list = new ArrayList<>();
    private void getBloodGroup(final Activity activity) {
        CustomProgressbar.Progressbarshow(activity);
        APIInterface api = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        api.getBloodgroup(RetrofitInstance.blood_groups).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.body() != null) {
                    try {

                        String responseRecieved = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseRecieved);
                        StoredObjects.LogMethod("response", "response::" + responseRecieved);
                        String status = jsonObject.getString("status");
                        if (status.equalsIgnoreCase("200")) {
                            String results = jsonObject.getString("results");
                            bloodgroup_list = JsonParsing.GetJsonData(results);
                            bloodgroupnames.clear();
                            for (int k = 0; k < bloodgroup_list.size(); k++) {

                                bloodgroupnames.add(bloodgroup_list.get(k).get("name"));
                            }

                        } else {
                            StoredObjects.ToastMethod("No Data found", activity);
                        }


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }


                CustomProgressbar.Progressbarcancel(activity);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                CustomProgressbar.Progressbarcancel(activity);


            }
        });
    }

    private void addPatientService(final Activity activity, String name_str, String email_str, String dob_str, String gender_str, String aadhar_str, String blood_grp_str, String phone_str, String password_str) {

        CustomProgressbar.Progressbarshow(activity);
        APIInterface api = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        api.addPatient(RetrofitInstance.add_patient,name_str,email_str,dob_str,gender_str,aadhar_str,blood_id,phone_str,password_str,StoredObjects.UserId,StoredObjects.UserRoleId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body() != null) {
                    try {
                        String responseReceived = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseReceived);
                        StoredObjects.LogMethod("response", "response::" + responseReceived);
                        String status = jsonObject.getString("status");
                        if (status.equalsIgnoreCase("200")) {
                            StoredObjects.ToastMethod("Added successfully!", activity);
                            fragmentcallinglay(new TL_Patients());
                        } else {
                            String error = jsonObject.getString("error");
                            StoredObjects.ToastMethod(error, activity);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                CustomProgressbar.Progressbarcancel(activity);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                CustomProgressbar.Progressbarcancel(activity);

            }
        });



    }

    private void GenderListPopup(final CustomEditText prfilenme,Activity activity){
        final ListPopupWindow listPopupWindow = new ListPopupWindow(activity);
        listPopupWindow.setAdapter(new ArrayAdapter<>(activity,R.layout.drpdwn_lay,genderlist));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(genderlist[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


}


