package com.rxmediandroidapp.storedobjects;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Android-9 on 03-08-2019.
 */

public class StoredObjects {

    public static String UserId = "0";
    public static String UserType = "0";
    public static int listcount = 0;
    public static String page_type = "";
    public static String back_type = "";
    public static String tab_type = "";
    public static int tabcount = 0;
    public static String Gridview="Grid";
    public static String Listview="List";
    public static int ver_orientation= LinearLayoutManager.VERTICAL;
    public static int horizontal_orientation= LinearLayoutManager.HORIZONTAL;

    //Log
    public static void LogMethod(String keyval, String val) {
        Log.i(keyval, val);
    }

    //Displaying toast
    public static void ToastMethod(String message, Context context) {
        Toast.makeText(context, message, 0).show();
    }

    public static boolean inputValidation(EditText edittext, String message, Activity activity) {
        if (edittext.getText().toString().trim().equals("") || edittext.getText().toString().trim().equals(null)) {
            Toast.makeText(activity, message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean isValidMobile(String phone) {

        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 9 && phone.length() <= 13;
        }
        return false;
    }


    public static void hide_keyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService( Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //Check Email Validation
    public static boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Spanned stripHtml(String html) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //Spanned htmlAsSpanned = Html.fromHtml(html);
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    public static ArrayList<HashMap<String, String>> dummy_list = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> menuitems_list = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> subdummy_list = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> innerdummy_list = new ArrayList<>();
    public static void hashmaplist(int val) {
        dummy_list.clear();

        for (int k = 0; k < val; k++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", "Alex Gosh");
            hashMap.put("is_viewed", "No");
            dummy_list.add(hashMap);
        }
    }

    public static void getrray(String[] array) {
        menuitems_list.clear();

        for (int k = 0; k < array.length; k++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", array[k]);
            menuitems_list.add(hashMap);
        }
    }

    public static void subhashmaplist(int val) {
        subdummy_list.clear();

        for (int k = 0; k < val; k++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", "Alex Gosh");
            hashMap.put("is_viewed", "No");
            subdummy_list.add(hashMap);
        }
    }

    public static void AlertForCall(final Activity activity, final String customer_care_number) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(customer_care_number)
                .setCancelable(false)
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (ContextCompat.checkSelfPermission(activity,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(activity,
                                        Manifest.permission.CALL_PHONE)
                                        != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);

                        } else {

                            StoredObjects.LogMethod("response", "response<><><><>><:---"+customer_care_number);

                            activity.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+customer_care_number))); }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }

                });

       //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void hide_dialogkeyboardtextview(Activity activity, AutoCompleteTextView editText) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }catch (Exception e){

        }
    }

}