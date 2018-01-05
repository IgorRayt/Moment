package com.moment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;



public class SettingsPreviewPageFragment extends Fragment {

    Context activityContext;
    Integer blooperCount = 0;
    String imagePath = "";

    public SettingsPreviewPageFragment() {
        // Required empty public constructor
    }


    public static SettingsPreviewPageFragment newInstance() {
        SettingsPreviewPageFragment fragment = new SettingsPreviewPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_preview_page, container, false);

        Button btn_open_settings = (Button) view.findViewById(R.id.btn_open_settings);
        Button btnDisplayDeveloper = view.findViewById(R.id.btn_display_developer);
        Button btnMail = view.findViewById(R.id.btn_mail);
        Button btnText = view.findViewById(R.id.btn_text);

        btn_open_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_open_settings_click(v);
            }
        });

        btnDisplayDeveloper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayDeveloper();
            }
        });

        btnMail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mailToDev();
            }
        });

        btnText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textToDev();
            }
        });

        return view;
    }

    public void displayDeveloper(){
        blooperCount += 1;
        if (blooperCount == 5) {
            blooperCount = 0;
            String file_url = "https://www.google.ca/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png";
            new DownloadFile().execute(file_url);

            BlooperFragment blooperFragment = new BlooperFragment();
            Bundle bundle = new Bundle();
            bundle.putString("imagePath", imagePath);
            blooperFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_bloop, blooperFragment,
                            blooperFragment.getClass().getSimpleName())
                    .addToBackStack(null).commit();
        }
    }

    public void mailToDev(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","wrightigor@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));


    }

    public void textToDev(){
        String number = "+15197215988";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
    }

    public void btn_open_settings_click(View view){
        activityContext = getActivity();
        Intent intent = new Intent(activityContext, SettingsActivity.class);
        startActivity(intent);
    }

    class DownloadFile extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                FileOutputStream fos = getContext().openFileOutput("downloadedfile.jpg", Context.MODE_PRIVATE);
                OutputStream output = fos;

                byte data[] = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            imagePath = getContext().getFilesDir()
                    + "/downloadedfile.jpg";
        }

    }


}
