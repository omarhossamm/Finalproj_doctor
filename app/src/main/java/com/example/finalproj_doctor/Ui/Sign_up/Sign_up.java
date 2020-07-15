package com.example.finalproj_doctor.Ui.Sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Location.Location;
import com.example.finalproj_doctor.Ui.Doctor_profile.Doctor_profile;
import java.util.Calendar;

public class Sign_up extends AppCompatActivity {

    EditText BD , name , password , con_password , phone , email ;

    Signup_Viewmodel signup_viewmodel;

    Spinner careers_spinner;

    Button continuous;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//startActivity(new Intent(getApplicationContext() , Doctor_profile.class));
        careers_spinner = findViewById(R.id.careers);

        BD = findViewById(R.id.birthday);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        con_password = findViewById(R.id.conf_password);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        continuous = findViewById(R.id.continuous);

        signup_viewmodel = new Signup_Viewmodel();

        ArrayAdapter<String> items = new ArrayAdapter<>(getApplicationContext()
                , android.R.layout.simple_spinner_dropdown_item, signup_viewmodel.Get_items());
        careers_spinner.setAdapter(items);

        BD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Birthday();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String Bd = year + "-" + month + "-" + dayOfMonth;
                BD.setText(Bd);

            }
        };



        continuous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name.length() == 0 ||email.length() == 0 || password.length() == 0 || con_password.length() == 0
                        || phone.length() == 0
                        || BD.length() == 0) {
                    Toast.makeText(getApplicationContext(), "برجاء ادخال كافه التفاصيل", Toast.LENGTH_LONG).show();


                }
                else  if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
                    Toast.makeText(getApplicationContext() , "برجاء كتابة البريد الالكترونى بشكل صحيح" , Toast.LENGTH_LONG).show();

                }
                else if (password.getText().toString().length() < 5) {
                    Toast.makeText(getApplicationContext(), "كلمه المرور ضعيفه", Toast.LENGTH_LONG).show();

                } else if (!password.getText().toString().equals(con_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "كلمه المرور غير متطابقه", Toast.LENGTH_LONG).show();

                }
                else {}


                Intent intent = new Intent(Sign_up.this , Location.class);
                intent.putExtra("name" , name.getText().toString());
                intent.putExtra("email" , email.getText().toString() );
                intent.putExtra("password" , password.getText().toString());
                intent.putExtra("phone" , phone.getText().toString());
                intent.putExtra("birthday" , BD.getText().toString());
                intent.putExtra("careers" , careers_spinner.getSelectedItem().toString());
                startActivity(intent);

            }
        });



    }


    public void Birthday(){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Sign_up.this
                , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                , onDateSetListener , year , month , day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
