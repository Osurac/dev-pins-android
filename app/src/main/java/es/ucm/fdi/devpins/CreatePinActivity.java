package es.ucm.fdi.devpins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.ucm.fdi.devpins.data.model.Pin;

public class CreatePinActivity extends AppCompatActivity {

    private EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
        initWidgets();
    }

    private void initWidgets() {
        urlEditText = findViewById(R.id.editTextNewPinUrl);
    }

    public void savePin(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String url = String.valueOf(urlEditText.getText());
        int id = Pin.pinArrayList.size();
        Pin newPin = new Pin(id, url, false);
        Pin.pinArrayList.add(newPin);
        sqLiteManager.addPinToDatabase(newPin);
        finish();
    }
}