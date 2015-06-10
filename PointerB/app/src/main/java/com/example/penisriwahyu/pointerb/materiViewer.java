package com.example.penisriwahyu.pointerb;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.penisriwahyu.pointerb.Model.Materi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class materiViewer extends ActionBarActivity {

    private Toolbar toolbar;
    private List<Materi> materis;
    LinearLayout rel;

    Button btnShowProgress;
    String file_name[] = {"1.png", "2.png", "3.png", "4.png", "5.png"};
    int file_counter = 5;

    // Progress Dialog
    private ProgressDialog pDialog;
    ImageView my_image;
    String path;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url = "http://128.199.140.247/pointschool/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_viewer);

        Intent intent = getIntent();
        Bundle bund = intent.getExtras();

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        rel = (LinearLayout) findViewById(R.id.materi);

        File materiDirectory = new File("/sdcard/PointerSchool/");
        if (!materiDirectory.exists())
            materiDirectory.mkdirs();
        materiDirectory = new File("/sdcard/PointerSchool/" + bund.getInt("id_subbab"));
        if (!materiDirectory.exists())
            materiDirectory.mkdirs();


        materiViewer.this.setTitle(bund.getString("nama_subbab"));
        DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
        materis = db.getMateri(bund.getInt("id_subbab"));
        for (int i = 0;i < materis.size();i++){
            //Log.d("HAHA-------------------", materis.get(i).getIdFileMateri());


            String string = materis.get(i).getIdFileMateri();
            String[] parts = string.split("/");
            String filename = parts[2];
            //Log.d("HAHAHAHA------------------", filename);
            new DownloadFileFromURL().execute(file_url+string, string, filename);

        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                Log.d("Alamat", f_url[0]);
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                String string = f_url[1];
                String[] parts = string.split("/");
                String path = parts[1];
                OutputStream output = new FileOutputStream("/sdcard/PointerSchool/" + path + "/" + f_url[2]);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return f_url[1];
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_name_dest) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String string = file_name_dest;
            String[] parts = string.split("/");
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/PointerSchool/"+ parts[1]+ "/" +parts[2];
            Log.d("INI imagePathNYA", imagePath);
            // setting downloaded into image view
            my_image = new ImageView(materiViewer.this);
            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
            my_image.setAdjustViewBounds(true);
            my_image.setScaleType(ImageView.ScaleType.FIT_START);
            rel.addView(my_image);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_materi_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
