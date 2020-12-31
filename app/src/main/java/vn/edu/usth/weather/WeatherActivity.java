package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.*;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    private class refresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Thread.sleep( 5000 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Void... voids) {
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getApplicationContext(),"fake data lmao", Toast.LENGTH_SHORT).show();
            return;
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
