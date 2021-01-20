package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        Log.i("Weather", "create");

//        lab11
//        MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
//        mp.start();

        //lab13

    }
//    public void refresh(){
//        final Handler hdl = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                String content = msg.getData().getString("server");
//                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
//            }
//        };
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("server", "fake data from json");
//                Message msg = new Message();
//                msg.setData(bundle);
//                hdl.sendMessage(msg);
//            }
//        });
//        t.start();
//    }

    private class refresh extends AsyncTask<Void, Integer, InputStream> {
        @Override
        protected InputStream doInBackground(Void... voids) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    ImageView iv = (ImageView) findViewById(R.id.whatever);
                    iv.setImageBitmap(response);
                }
            };

            ImageRequest imageRequest = new ImageRequest(
                    "https://usth.edu.vn/uploads/logo_moi-eng.png",
                    listener, 0, 0, ImageView.ScaleType.CENTER,
                    Bitmap.Config.ARGB_8888,null);

            queue.add(imageRequest);
            return null;

//            InputStream is = null;
//            try
//            {
//                URL url = new URL("https://usth.edu.vn/uploads/logo_moi-eng.png");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setDoInput(true);
//                connection.connect();
//                //response
//                int response = connection.getResponseCode();
//                Log.i("USTH blabla", "Le response is :" + response);
//                is = connection.getInputStream();
//                //process image
//
//                Log.i("Hello", "got logo");
//
//                connection.disconnect();
//
//            }
//            catch (IOException e )
//            {
//                e.printStackTrace();
//            }
//            return is;

        }

        protected void onProgressUpdate(Void... voids) {
        }

        @Override
        protected void onPostExecute(InputStream is) {
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            ImageView logo = (ImageView) findViewById(R.id.whatever);
//            logo.setImageBitmap(bitmap);
//            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        copyFileToExternalStorage(R.raw.sample, "song.mp3");
        Log.i("Weather", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Weather", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Weather", "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Weather", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Weather", "Destroy");
    }


    private void copyFileToExternalStorage(int resourceId, String resourceName) {
        try {
            File file = new File(getExternalFilesDir(null), resourceName);
            InputStream in = getApplicationContext().getResources().openRawResource(resourceId);
            OutputStream out = new FileOutputStream(file);
            byte[] buff = new byte[1024 * 2];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            }
            finally {
                Toast toast = Toast.makeText(getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_LONG);
                toast.show();
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_LONG);
            toast.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //lab12
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                new refresh().execute();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
