package hashtech.com.booklisting.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

import hashtech.com.booklisting.R;

public class SettingsActivity extends AppCompatActivity {

    private Switch switctchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        switctchView = (Switch) findViewById(R.id.switchView);

        switctchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if (on) {
                    changeLocal("ar");
                }else{
                   changeLocal("en");

                }
                switctchView.setText(R.string.lang);



            }
        });


    }


    public void changeLocal(String local){
        Locale locale = new Locale(local);
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


    @Override
    public void onBackPressed() {




        super.onBackPressed();

    }
}
