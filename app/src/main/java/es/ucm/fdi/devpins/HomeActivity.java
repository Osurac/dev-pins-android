package es.ucm.fdi.devpins;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import es.ucm.fdi.devpins.ui.main.SectionsPagerAdapter;
import es.ucm.fdi.devpins.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private int user_id;

    /**
     * Función onCreate de HomeActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle b = getIntent().getExtras();
        user_id = b.getInt("user_id");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    /**
     * Función que inicia la activity de CreatePinActivity
     * @param view
     */
    public void newPin(View view){
        Intent newPinIntent = new Intent(this, CreatePinActivity.class);
        newPinIntent.putExtra("user_id", user_id);
        startActivity(newPinIntent);
    }

    /**
     * Función que devuelve el user_id
     * @return
     */
    public int getUserId(){
        return user_id;
    }
}