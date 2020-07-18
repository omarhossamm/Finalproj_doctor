package com.example.finalproj_doctor.Ui.Schedule_update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Schedule;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Appointment_edit.Myappointment_edit;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule_update extends AppCompatActivity {
    EditText date , time_start , cost  , time_end;
    Spinner system_work;
    TextView day;
    Button confirmation;
    Schedule schedule1;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int hour , minutes;
    String day_arab;
    int yearr , monthh , dayy;
    Sceduleupdate_Viewmodel sceduleupdate_viewmodel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put__appointment);

        sceduleupdate_viewmodel = new Sceduleupdate_Viewmodel();
        sceduleupdate_viewmodel = ViewModelProviders.of(Schedule_update.this).get(Sceduleupdate_Viewmodel.class);
        Intent intent = getIntent();
        String schedule = intent.getStringExtra("schedule");
        Gson gson = new Gson();
        schedule1 = gson.fromJson(schedule , Schedule.class);

        date = findViewById(R.id.date_work);
        time_start = findViewById(R.id.time_start);
        time_end = findViewById(R.id.time_end);
        cost = findViewById(R.id.cost);
        system_work = findViewById(R.id.system);
        confirmation = findViewById(R.id.confirming_btn_confirming);

        day = findViewById(R.id.day_txt_confirming);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        final SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm");


        final Calendar startat = Calendar.getInstance();
        Calendar endat = Calendar.getInstance();
        startat.setTimeInMillis(schedule1.getStartedAt());
        endat.setTimeInMillis(schedule1.getEndedAt());
        final String start = formatter.format(startat.getTime());
        String end = formatter1.format(endat.getTime());


        day.setText(schedule1.getDay());
        date.setText(start);
        time_start.setText(formatter1.format(startat.getTime()));
        time_end.setText(end);
        cost.setText("" + schedule1.getSessionCost());
        addItemsOnSpinner();
        Start_Time();
        End_Time();
        Date_Picker();


        sceduleupdate_viewmodel.getresponse().observe(Schedule_update.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if (s.equals("Schedule update")){
                    confirmation.setEnabled(true);
                    confirmation.setBackgroundColor(getResources().getColor(R.color.color_des));
                    startActivity(new Intent(getApplicationContext() , Myappointment_edit.class));
                }else {
                    confirmation.setEnabled(true);
                    confirmation.setBackgroundColor(getResources().getColor(R.color.color_des));
                }
            }
        });

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                com.example.finalproj_doctor.Model.Schedule_update schedule = new com.example.finalproj_doctor.Model.Schedule_update(system_work.getSelectedItem().toString(), day.getText().toString(),
                millis_start, millis_end, Double.parseDouble(cost.getText().toString()));
                confirmation.setEnabled(false);
                confirmation.setBackgroundColor(getResources().getColor(R.color.loading));
                sceduleupdate_viewmodel.Update_schedule(schedule1.get_id().toString() , context = Schedule_update.this , schedule);
            }
        });


    }

    public void addItemsOnSpinner() {
        List<String> list = new ArrayList<>();
        list.add(schedule1.getBookSystem());
            if (schedule1.getBookSystem().equals("byNumber")){
                list.add("byTime");
            }else {
                list.add("byNumber");
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            system_work.setAdapter(adapter);
    }



    public void Start_Time() {

        time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Time_Picker(time_start);

            }


        });
    }

    public void End_Time(){

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

                DatePickerDialog dialog = new DatePickerDialog(Schedule_update.this
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
        public void Time_Picker(final EditText time){


            TimePickerDialog timePickerDialog = new TimePickerDialog(Schedule_update.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    view.setIs24HourView(true);
                    hour = hourOfDay;
                    minutes = minute;

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(0, 0, 0, hour , minutes);

                    time.setText(DateFormat.format("HH:mm" , calendar));

                }
            },12 , 0 , false

            );

            timePickerDialog.updateTime(hour , minutes);
            timePickerDialog.show();


        }


    }
