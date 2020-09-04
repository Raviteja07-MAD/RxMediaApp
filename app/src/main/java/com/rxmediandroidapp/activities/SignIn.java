package com.rxmediandroidapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomBoldTextView;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.storedobjects.StoredObjects;

public class SignIn extends Activity {
    TextView sgn_cratacnt_txt,sgn_forgotpswd_txt;
    CustomButton sgn_sgnin_btn;
    EditText email_edttx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        initialisation();
    }

    public void initialisation() {

        sgn_cratacnt_txt =  findViewById(R.id.sgn_cratacnt_txt);
        sgn_forgotpswd_txt =  findViewById(R.id.sgn_forgotpswd_txt);
        sgn_sgnin_btn =  findViewById(R.id.sgn_sgnin_btn);
        email_edttx=findViewById(R.id.email_edttx);

        sgn_sgnin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=email_edttx.getText().toString().trim();
               // email_edttx.setText("Hospital");
                if(StoredObjects.inputValidation(email_edttx,"Please Enter Patient/Doctor/Hospital",SignIn.this)){
                    if(username.equalsIgnoreCase("Patient")||username.equalsIgnoreCase("Doctor")||
                            username.equalsIgnoreCase("Hospital")){
                        StoredObjects.UserType=username;
                        StoredObjects.listcount=0;
                        SignIn.this.finish();
                        startActivity(new Intent(SignIn.this, SideMenu.class));

                    }else{
                        StoredObjects.ToastMethod("Please Enter Patient/Doctor/Hospital",SignIn.this);
                    }

                }

            }
        });


        sgn_cratacnt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, SignupOptions.class));
            }
        });

        sgn_forgotpswd_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, ForgotPassword.class));
            }
        });

    }
}
