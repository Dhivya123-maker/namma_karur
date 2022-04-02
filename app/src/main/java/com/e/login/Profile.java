package com.e.login;

import static android.view.View.GONE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.login.AmbulanceClass.Ambulance;
import com.e.login.AmbulanceClass.AmbulanceAdapter;
import com.e.login.AmbulanceClass.AmbulanceModel;
import com.e.login.HomeClass.Home;
import com.e.login.Profile_details.EducationAdapter;
import com.e.login.Profile_details.Education_Model;
import com.e.login.Profile_details.Education_details;
import com.e.login.ShopClass.ShopScreen_Class;
import com.e.login.Verification.Change_Email_OTP;
import com.e.login.Verification.Change_Phone;
import com.e.login.Verification.Edit;
import com.e.login.Verification.Email_OTP;
import com.e.login.Verification.Email_Verification;
import com.e.login.Verification.Phone_txt;
import com.e.login.Verification.VerificationActivity;
import com.e.login.Verification.VerifyActivity;
import com.e.login.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Profile extends AppCompatActivity {
    Button btn, edit, save, email_verify, contact_verify;
    String data, data1, data2, goo_id,dobb;
    TextView user_name, emailtxt, contacttxt,b_gp,dob;
    LinearLayout skill;
    ImageView profile, email_edit, con_edit, edu_add, skill_add,choose_img;

    Context mContext;
    int SELECT_PICTURE = 1;
    Popup_Adapter adapter;


    String data3;
    View view, view1, view2, view3;
    String id, email, phone, namee, email_verifyy, phone_verifyy, image;
    Button edit_btn;
    String Email_get, Phone_get;

    private ImageView exp_add = null;
    private ListView userDataListView = null;
    // Below edittext and button are all exist in the popup dialog view.
    private View popupInputDialogView = null;
    // Contains user name data.
    private EditText userNameEditText = null;
    // Contains password data.
    private EditText passwordEditText = null;
    // Contains email data.
    private EditText emailEditText = null;
    // Click this button in popup dialog to save user input data in above three edittext.
    private Button saveUserDataButton = null;
    // Click this button to cancel edit user data.
    private Button cancelUserDataButton = null;
    RecyclerView recyclerView ;

//    List<Education_Model> educationModelList;
//    EducationAdapter adapter;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);



        Intent intent = getIntent();
        data = intent.getStringExtra("token");
        data1 = intent.getStringExtra("id");
        data2 = intent.getStringExtra("user_name");
        data3 = intent.getStringExtra("email");






        choose_img = findViewById(R.id.img_choose);
        choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        get_profile();



        view = findViewById(R.id.first_view);
        view2 = findViewById(R.id.third_view);
        view3 = findViewById(R.id.fourth_view);
        user_name = findViewById(R.id.user_name);


        btn = findViewById(R.id.change_btn);


        edu_add = findViewById(R.id.edu_add);
        exp_add = findViewById(R.id.exp_add);
        skill_add = findViewById(R.id.skill_add);
        dob = findViewById(R.id.dob);
        b_gp = findViewById(R.id.blood_gp);




        profile = findViewById(R.id.profile_img);
        email_edit = findViewById(R.id.gmail_edit);
        con_edit = findViewById(R.id.contact_edit);

        emailtxt = findViewById(R.id.emailtxt);
        contacttxt = findViewById(R.id.contacttxt);
        email_verify = findViewById(R.id.email_verify);
        contact_verify = findViewById(R.id.contact_verify);

        edit_btn = findViewById(R.id.edit_button);
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PopUpClass popUpClass = new PopUpClass();
                popUpClass.showPopupWindow(getSupportFragmentManager(),view);

            }
        });


        edu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                PopUpClass popUpClass = new PopUpClass();
//                popUpClass.showPopupWindow(view);
                popUpClass.showPopupWindow(getSupportFragmentManager(),view);




            }


        });






        emailtxt.setText(data3);

        email_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send_mail();

            }
        });


        contact_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent intent1 = new Intent(Profile.this,VerifyActivity.class);
//                startActivity(intent1);
//                send_mail();
            }
        });

        email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Profile.this, Email_Verification.class);
                intent1.putExtra("id",data1);
                intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);


//                change_email();

            }
        });


        con_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                change_phone();
                Intent intent1 = new Intent(Profile.this, Phone_txt.class);
                intent1.putExtra("id",data1);
                intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);

            }
        });


        edit = findViewById(R.id.edit_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ChangePassword.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("token", data);
                intent.putExtra("id", data1);
                startActivity(intent);
            }
        });


    }


    public void send_mail() {


        String url = "http://nk.inevitabletech.email/public/api/sendEmailOtp";
        JSONObject jsonBody = new JSONObject();


        try {
            jsonBody.put("user_id", data1);



            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

//                    Log.i("0000000000000",response.toString());
//                    Toast.makeText(Profile.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        String Success = response.getString("success");
                        String msg = response.getString("message");


                        if (Success.equals("true")) {
                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(Profile.this, Email_OTP.class);
                            intent1.putExtra("email", data3);
                            intent1.putExtra("id", data1);
//
                            intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);


                        } else {


                            Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Charset charset = Charset.defaultCharset();
                    String str = new String(error.networkResponse.data, charset);


                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");

                        String err = data.getString("error");
                        Toast.makeText(Profile.this, err, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    params.put("Authorization", "Bearer  " + PreferenceUtils.getToken(Profile.this));
                    return params;


                }
            };

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
            requestQueue.add(jsonObjectRequest);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void get_profile() {

        String JSON_URL = "http://nk.inevitabletech.email/public/api/get-profile-details";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(JSONObject response) {


                try {


                    String Success = response.getString("success");
                    String msg = response.getString("message");


                    JSONObject jsonObject = response.getJSONObject("data");
                    id = jsonObject.getString("id");
                    namee = jsonObject.getString("name");
                    email = jsonObject.getString("email");
                    phone = jsonObject.getString("phone");
                    email_verifyy = jsonObject.getString("email_verification_status");
                    phone_verifyy = jsonObject.getString("phone_verification_status");
                    image = jsonObject.getString("image");


                    if (Success.equals("true")) {
                        Log.i("123", msg);


                        emailtxt.setText(email);
                        contacttxt.setText(phone);
                        user_name.setText(namee);
                        email_verify.setText(email_verifyy);
                        contact_verify.setText(phone_verifyy);


//                        profile.setImageURI(Uri.parse(image));


                    } else {
                        Log.i("1234", msg);
                        Toast.makeText(Profile.this, msg, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }


            }

//
//
//        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, "Not", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();


                headers.put("Authorization", "Bearer " + PreferenceUtils.getToken(Profile.this));
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(jsonObjectRequest);


    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    profile.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public class PopUpClass {



        public void showPopupWindow(FragmentManager supportFragmentManager, final View view) {




            //Create a View object yourself through inflater
            LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_screen, null);

            //Specify the length and width through constants
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;

            //Make Inactive Items Outside Of PopupWindow
            boolean focusable = true;

            //Create a window with our parameters
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            //Set the location of the window on the screen
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            //Initialize the elements of our window, install the handler




            //Handler for clicking on the inactive zone of the window

            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    //Close the window when clicked
                    popupWindow.dismiss();
                    return true;
                }
            });

            Button cancel = (Button) popupView.findViewById(R.id.cancel);
            Button save = (Button) popupView.findViewById(R.id.save);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditText ins = popupView.findViewById(R.id.ins);
                    EditText deg = popupView.findViewById(R.id.deg);
                    EditText year = popupView.findViewById(R.id.yr);

                    String Ins = ins.getText().toString();
                    String Deg = deg.getText().toString();
                    String Year = year.getText().toString();

                    popupWindow.dismiss();



                    ArrayList<String> data = new ArrayList<>();
                    data.add(Ins);
                    data.add(Deg);
                    data.add(Year);


                    recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
                    adapter = new Popup_Adapter(Profile.this,data);
                    recyclerView.setAdapter(adapter);


                }
            });






        }

    }



//    public void onButtonShowPopupWindowClick(View view) {
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.popup_screen, null);
//
//        // create the popup window
////        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
////        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
////        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//
//    }

}

//    private void initMainActivityControls()
//    {
//        if(edu_add == null)
//        {
//            edu_add = (ImageView) findViewById(R.id.edu_add);
//        }
//        if(recyclerView == null)
//        {
//            recyclerView = (RecyclerView) findViewById(R.id.recycler);
//        }
//    }
//    /* Initialize popup dialog view and ui controls in the popup dialog. */
//    private void initPopupViewControls()
//    {
//        // Get layout inflater object.
//        LayoutInflater layoutInflater = LayoutInflater.from(Profile.this);
//        // Inflate the popup dialog from a layout xml file.
//        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);
//        // Get user input edittext and button ui controls in the popup dialog.
//        userNameEditText = (EditText) popupInputDialogView.findViewById(R.id.Institute);
//        passwordEditText = (EditText) popupInputDialogView.findViewById(R.id.Degree);
//        emailEditText = (EditText) popupInputDialogView.findViewById(R.id.year);
//
//        saveUserDataButton = popupInputDialogView.findViewById(R.id.button_save_user_data);
//        cancelUserDataButton = popupInputDialogView.findViewById(R.id.button_cancel_user_data);
//        // Display values from the main activity list view in user input edittext.
//        initEditTextUserDataInPopupDialog();
//    }
//    /* Get current user data from listview and set them in the popup dialog edittext controls. */
//    private void initEditTextUserDataInPopupDialog()
//    {
//        List<String> userDataList = getExistUserDataInListView(recyclerView);
//        if(userDataList.size() == 3)
//        {
//            String userName = userDataList.get(0);
//            String password = userDataList.get(1);
//            String email = userDataList.get(2);
//            if(userNameEditText != null)
//            {
//                userNameEditText.setText(userName);
//            }
//            if(passwordEditText != null)
//            {
//                passwordEditText.setText(password);
//            }
//            if(emailEditText != null)
//            {
//                emailEditText.setText(email);
//            }
//        }
//    }
//    /* If user data exist in the listview then retrieve them to a string list. */
//    private List<String> getExistUserDataInListView(RecyclerView listView)
//    {
//        List<String> ret = new ArrayList<String>();
//        if(listView != null)
//        {
//            EducationAdapter listAdapter = (EducationAdapter) listView.getAdapter();
//            if(listAdapter != null) {
//                int itemCount = listAdapter.getCount();
//                for (int i = 0; i < itemCount; i++) {
//                    Object itemObject = listAdapter.getItem(i);
//                    HashMap<String, String> itemMap = (HashMap<String, String>)itemObject;
//                    Set<String> keySet = itemMap.keySet();
//                    Iterator<String> iterator = keySet.iterator();
//                    String key = iterator.next();
//                    String value = itemMap.get(key);
//                    ret.add(value);
//                }
//            }
//        }
//        return ret;
//    }

//            }