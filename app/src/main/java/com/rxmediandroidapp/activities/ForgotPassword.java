package com.rxmediandroidapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.rxmediandroidapp.R;
import com.rxmediandroidapp.Sidemenu.SideMenu;
import com.rxmediandroidapp.customfonts.CustomBoldTextView;
import com.rxmediandroidapp.customfonts.CustomButton;
import com.rxmediandroidapp.customfonts.CustomEditText;

public class ForgotPassword extends Activity {

    ImageView forgot_back_img;
    CustomEditText forgot_mbile_edtx;
    CustomButton forgot_reset_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        initialisation();
    }

    public void initialisation() {

        forgot_back_img =  findViewById(R.id.forgot_back_img);
        forgot_mbile_edtx =  findViewById(R.id.forgot_mbile_edtx);
        forgot_reset_btn =  findViewById(R.id.forgot_reset_btn);

        forgot_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ForgotPassword.this.finish();
            }
        });

        forgot_reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  startActivity(new Intent(ForgotPassword.this, SideMenu.class));
            }
        });


    }
}
