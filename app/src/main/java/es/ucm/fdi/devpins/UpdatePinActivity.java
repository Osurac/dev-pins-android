package es.ucm.fdi.devpins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import es.ucm.fdi.devpins.data.adapter.PinAdapter;
import es.ucm.fdi.devpins.data.model.Pin;

public class UpdatePinActivity extends AppCompatActivity {

    private EditText urlEditText;
    private String type;
    private Boolean fav;
    private Switch switchM;
    private Pin selectedPin;
    private Button runButton;

    /**
     * Función onCreate de UpdatePinActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pin);
        initWidgets();
        checkEditPin();
    }

    /**
     * Función que recoge el pin seleccionado
     */
    private void checkEditPin() {
        Intent prev = getIntent();
        int idPinToEdit = prev.getExtras().getInt(Pin.PIN_EDIT_EXTRA, -1);
        selectedPin = Pin.getPinFromId(idPinToEdit);
        if(selectedPin != null){
            urlEditText.setText(selectedPin.getUrl());
            switchM.setChecked(selectedPin.getFav());
            fav = selectedPin.getFav();
            type = selectedPin.getType();
        }
    }
    /**
     * Función de inicizalicación de widgets
     */
    private void initWidgets() {
        urlEditText = findViewById(R.id.editTextUpdatePinUrl);
        switchM = (Switch) findViewById(R.id.switchFavEdit);
        runButton = findViewById(R.id.buttonActionPin);

        if (switchM != null) {
            switchM.setOnCheckedChangeListener(this::onCheckedChanged);
        }

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("yt")){
                    Intent i = new Intent(UpdatePinActivity.this, YoutubeActivity.class);
                    i.putExtra("url", selectedPin.getUrlIdYoutube());
                    finish();
                    startActivity(i);
                }else if(type.equals("basic")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedPin.getUrl()));
                    startActivity(browserIntent);
                }else{
                    Intent i = new Intent(UpdatePinActivity.this, PodActivity.class);
                    i.putExtra("url", selectedPin.getUrl());
                    finish();
                    startActivity(i);
                }

            }
        });
    }

    /**
     * Función de actualización de pin en bbdd
     * @param view
     */
    public void updatePin(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        if(selectedPin == null){
            String url = String.valueOf(urlEditText.getText());
            int id = Pin.pinArrayList.size();
            Bundle b = getIntent().getExtras();
            int user_id = b.getInt("user_id");
            Pin newPin = new Pin(id, user_id, url, type, fav);
            Pin.pinArrayList.add(newPin);
            sqLiteManager.addPinToDatabase(newPin);

        }else{
            selectedPin.setUrl(urlEditText.getText().toString());
            selectedPin.setFav(fav);
            sqLiteManager.updatePinInDatabase(selectedPin);
        }
        finish();
    }

    /**
     * función que chequea que radio button ha sido pulsado
     * @param view
     */
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


    /**
     * función que chequea si cambió el switch
     * @param buttonView
     * @param isChecked
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            fav = true;
        } else {
            fav = false;
        }
    }

    /**
     * Función que elimina un pin
     * @param view
     */
    public void deletePin(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.deletePinInDatabase(selectedPin);
        Pin.pinArrayList.remove(selectedPin);
        PinAdapter pinAdapter = new PinAdapter(this, Pin.pinArrayList);
        pinAdapter.notifyDataSetChanged();
        switch (selectedPin.getType()){
            case "basic":   Pin.pinArrayListBasic.remove(selectedPin);
                pinAdapter = new PinAdapter(this, Pin.pinArrayListBasic);
                break;
            case "yt":
                Pin.pinArrayListYT.remove(selectedPin);
                pinAdapter = new PinAdapter(this, Pin.pinArrayListYT);
                break;
            case "pod":
                Pin.pinArrayListPod.remove(selectedPin);
                pinAdapter = new PinAdapter(this, Pin.pinArrayListPod);
                break;
        }
        pinAdapter.notifyDataSetChanged();
        finish();
    }
}