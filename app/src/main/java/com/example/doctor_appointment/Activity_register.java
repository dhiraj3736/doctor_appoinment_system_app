package com.example.doctor_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_register extends AppCompatActivity {

    EditText name,email,number,password;
    Button register,login;
    String mname,memail,mnumber,mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.full_name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.phone_number);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_register.this,login.class);
                startActivity(intent);
            }
        });
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mname=name.getText().toString().trim();
        memail=email.getText().toString().trim();
        mnumber=number.getText().toString().trim();
        mpassword=password.getText().toString().trim();

        if (mname.isEmpty()){
            Toast.makeText(Activity_register.this, "please enter fullname", Toast.LENGTH_SHORT).show();

            return;
        } else if (memail.isEmpty()){
            Toast.makeText(Activity_register.this, "please enter email", Toast.LENGTH_SHORT).show();

            return;
        }
        else if (mnumber.isEmpty()){
            Toast.makeText(Activity_register.this, "please enter number", Toast.LENGTH_SHORT).show();

            return;
        }
        else if  (mpassword.isEmpty()){
            Toast.makeText(Activity_register.this, "please enter password", Toast.LENGTH_SHORT).show();

            return;
        }
        else if (!mnumber.matches("^(97|98)\\d{8}$")) {
            Toast.makeText(Activity_register.this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();

            return;
        }
        else if (!memail.matches("^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)@[a-z]+(.[a-z]+)(.[a-z]{2,})$")) {
            Toast.makeText(Activity_register.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }


        String url =Endpoints.register;
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_register.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    if (jsonObject.getString("result").equals("success")){
                        Toast.makeText(Activity_register.this, "Registration successful!", Toast.LENGTH_SHORT).show();


                        name.setText("");

                        email.setText("");
                        number.setText("");
                        password.setText("");
                        register.setClickable(false);
                    }else if (response.equals("failure")) {
                        Toast.makeText(Activity_register.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_register.this, "Error parsing server response", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> data=new HashMap<>();
                data.put("fullname",mname);
                data.put("email",memail);
                data.put("number",mnumber);
                data.put("password",mpassword);
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

});

    }



}