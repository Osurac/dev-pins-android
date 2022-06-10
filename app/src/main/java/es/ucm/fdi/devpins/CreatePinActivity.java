package es.ucm.fdi.devpins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import es.ucm.fdi.devpins.data.model.Pin;

public class CreatePinActivity extends AppCompatActivity {

    private EditText urlEditText;
    private String type;
    private Boolean fav;
    private Switch switchM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
        initWidgets();
    }

    private void initWidgets() {
        urlEditText = findViewById(R.id.editTextNewPinUrl);
        switchM = (Switch) findViewById(R.id.switchFav);
        if (switchM != null) {
            switchM.setOnCheckedChangeListener(this::onCheckedChanged);
        }
    }

    public void savePin(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String url = String.valueOf(urlEditText.getText());
        int id = Pin.pinArrayList.size();
        Bundle b = getIntent().getExtras();
        int user_id = b.getInt("user_id");
        Pin newPin = new Pin(id, user_id, url, type, fav);
        Pin.pinArrayList.add(newPin);
        if(fav) Pin.pinArrayFavList.add(newPin);
        sqLiteManager.addPinToDatabase(newPin);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_basic:
                if (checked)
                    type = "basic";
                    break;
            case R.id.radio_yt:
                if (checked)
                    type = "yt";
                    break;
            case R.id.radio_pod:
                if (checked)
                    type = "pod";
                    break;
        }
    }


    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            fav = true;
        } else {
            fav = false;
        }
    }
}