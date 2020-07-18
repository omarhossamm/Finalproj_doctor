package com.example.finalproj_doctor.Ui.Put_Appointment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Location.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Put_Appointment extends Fragment {

    EditText date, time_start, cost, time_end;
    Spinner system_work;
    TextView day;
    int hour, minutes;
    String day_arab;
    Button confirmation;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int yearr, monthh, dayy;
    Calendar calendar;
    Putappointment_Viewmodel putappointment_viewmodel;
    Doctor_pref doctor_pref;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.activity_put__appointment, null);


        doctor_pref = new Doctor_pref(getContext(), "Data");

        putappointment_viewmodel = new Putappointment_Viewmodel();
        putappointment_viewmodel = ViewModelProviders.of(Put_Appointment.this).get(Putappointment_Viewmodel.class);

        date = layout.findViewById(R.id.date_work);
        time_start = layout.findViewById(R.id.time_start);
        time_end = layout.findViewById(R.id.time_end);
        cost = layout.findViewById(R.id.cost);
        system_work = layout.findViewById(R.id.system);
        confirmation = layout.findViewById(R.id.confirming_btn_confirming);

        day = layout.findViewById(R.id.day_txt_confirming);


        putappointment_viewmodel.get_response().observe(Put_Appointment.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                if (s.equals("Schedule added")){
                    confirmation.setEnabled(true);
                    confirmation.setBackgroundColor(getResources().getColor(R.color.color_des));
                }else {
                    confirmation.setEnabled(true);
                    confirmation.setBackgroundColor(getResources().getColor(R.color.color_des));
                }
            }
        });

        Date_Picker();
        Start_Time();
        End_Time();
        Spinner_System();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd HH:mm");
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm aa");


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong("1592733600000"));
        String x = formatter.format(calendar.getTime());
        String y = formatter1.format(calendar.getTime());

        //Toast.makeText(getApplicationContext() , " " + y , Toast.LENGTH_LONG).show();


        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.length() == 0 || time_start.length() == 0 || time_end.length() == 0
                        || cost.length() == 0) {
                    Toast.makeText(getContext(), "برجاء كتابة كافة البيانات", Toast.LENGTH_LONG).show();
                } else {
                    long millis_start = 0, millis_end = 0;
                    try {
                        String start = date.getText().toString() + " " + time_start.getText().toString();
                        String end = date.getText().toString() + " " + time_end.getText().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
                        Date startt = sdf.parse(start);
                        Date endd = sdf.parse(end);
                        millis_start = startt.getTime();
                        millis_end = endd.getTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    confirmation.setEnabled(false);
                    confirmation.setBackgroundColor(getResources().getColor(R.color.loading));
                    Schedule schedule = new Schedule(system_work.getSelectedItem().toString(), day.getText().toString(),
                            0, 0, millis_start, millis_end
                            , Double.parseDouble(cost.getText().toString()));
                    putappointment_viewmodel.Post_Schedule(doctor_pref.get_Token(), schedule);
                }
            }
        });


        return layout;

    }

    public void Start_Time() {

        time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Time_Picker(time_start);

            }


        });
    }

    public void End_Time() {

        time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Time_Picker(time_end);

            }
        });

    }

    public void Date_Picker() {
        calendar = Calendar.getInstance();
        yearr = calendar.get(Calendar.YEAR);
        monthh = calendar.get(Calendar.MONTH);
        dayy = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getContext()
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , onDateSetListener, yearr, monthh, dayy);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String datee = year + "-" + month + "-" + dayOfMonth;


                try {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
                    Date dateee = sdf.parse(datee);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateee);


                    if (Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Monday";
                    } else if (Calendar.TUESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Tuesday";
                    } else if (Calendar.WEDNESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Wednesday";
                    } else if (Calendar.THURSDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Thursday";
                    } else if (Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Friday";
                    } else if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Saturday";
                    } else if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
                        day_arab = "Sunday";
                    }

                    day.setText(day_arab);


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                date.setText(datee);

            }
        };


    }

    public void Time_Picker(final EditText time) {


        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                view.setIs24HourView(true);
                hour = hourOfDay;
                minutes = minute;

                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, hour, minutes);

                time.setText(DateFormat.format("HH:mm", calendar));

            }
        }, 12, 0, false

        );

        timePickerDialog.updateTime(hour, minutes);
        timePickerDialog.show();


    }

    public void Spinner_System() {

        ArrayList<String> itemss = new ArrayList<>();
        itemss.add("byNumber");
        itemss.add("byTime");

        ArrayAdapter<String> items = new ArrayAdapter<>(getContext()
                , android.R.layout.simple_spinner_dropdown_item, itemss);
        system_work.setAdapter(items);

    }
    }



