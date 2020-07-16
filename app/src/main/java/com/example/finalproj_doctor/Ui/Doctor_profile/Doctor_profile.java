package com.example.finalproj_doctor.Ui.Doctor_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproj_doctor.Model.Doctor;
import com.example.finalproj_doctor.Model.Review;
import com.example.finalproj_doctor.Pref.Doctor_pref;
import com.example.finalproj_doctor.Model.Upload_response;
import com.example.finalproj_doctor.Network.Client;
import com.example.finalproj_doctor.Network.RetrofitApi;
import com.example.finalproj_doctor.R;
import com.example.finalproj_doctor.Ui.Location_update.Location_update;
import com.example.finalproj_doctor.Ui.Personal_information.Personal_information;
import com.example.finalproj_doctor.Ui.Review_doc.Review_doc;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_profile extends AppCompatActivity {

    Doctorprofile_Viewmodel doctorprofile_viewmodel;
    Context context;
    TextView doc_name, doc_spec, doc_reviews;
    RatingBar doc_rating;
    CircleImageView doc_pic;
    Doctor_pref doctor_pref;
    LinearLayout personal_information , location_linear , review_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        doc_pic = findViewById(R.id.doctor_pic);
        personal_information = findViewById(R.id.personal_information_linear);
        location_linear = findViewById(R.id.location_linear);
        review_linear = findViewById(R.id.reviews_linear);

        doctor_pref = new Doctor_pref(context = Doctor_profile.this , "Data");

        Permission();


        doc_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose();
            }
        });

        personal_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor_profile.this , Personal_information.class));
            }
        });

        location_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor_profile.this , Location_update.class));
            }
        });

        review_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor_profile.this , Review_doc.class));
            }
        });

        doc_name = findViewById(R.id.name_doctor);
        doc_spec = findViewById(R.id.doctor_spec);
        doc_reviews = findViewById(R.id.doctor_reviews_txt);
        doc_rating = findViewById(R.id.doctor_rating);


        doctorprofile_viewmodel = new Doctorprofile_Viewmodel();
        doctorprofile_viewmodel = ViewModelProviders.of(Doctor_profile.this).get(Doctorprofile_Viewmodel.class);

                doc_name.setText(doctor_pref.getData().getName());

                String x = "";
                for (int i = 0; i < doctor_pref.getData().getSpeciality().size(); i++) {
                    x += doctor_pref.getData().getSpeciality().get(i) + " , ";
                }
                doc_spec.setText(x.substring(0, x.length() - 2));
                doc_rating.setRating(doctor_pref.getData().getAverageRating());

                Picasso.get().load("https://sleepy-dusk-06409.herokuapp.com/uploads/" + doctor_pref.getData().getPhoto()).into(doc_pic);


                doctorprofile_viewmodel.getreviews(context = Doctor_profile.this);
                doctorprofile_viewmodel.review_response().observe(Doctor_profile.this, new Observer<List<Review>>() {
                    @Override
                    public void onChanged(List<Review> reviews) {

/*
                        Gson gson = new Gson();
                        String data = gson.toJson(reviews);
*/
                    }
                });

                doctorprofile_viewmodel.getcount().observe(Doctor_profile.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        doc_reviews.setText("(" + integer + " review)");
                    }
                });



    }

    public void choose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "اختار الصورة"), 1);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            if (resultCode == Activity.RESULT_OK) {


                 Uri   originalUri = data.getData();
                String    filePath = getPath(context = Doctor_profile.this , originalUri);

                File file = new File(filePath);
                doc_pic.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));

                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

                doctorprofile_viewmodel.photo(context = Doctor_profile.this , body);
                doctorprofile_viewmodel.getresponse().observe(Doctor_profile.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getApplicationContext() , s , Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                recreate();
            }
            else {
                Toast.makeText(getApplicationContext() , "Denied" , Toast.LENGTH_LONG).show();
            }
        }
    }


    public void Permission() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Doctor_profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    , 1);

        } else {


        }

    }




    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;


        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }

            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }

        else if ("content".equalsIgnoreCase(uri.getScheme())) {


            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}