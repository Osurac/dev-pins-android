package es.ucm.fdi.devpins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import es.ucm.fdi.devpins.data.model.Pin;
import es.ucm.fdi.devpins.data.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidgets();
    }

    private void initWidgets() {
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
    }

    public void login(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String email = String.valueOf(emailEditText.getText());
        String pwd = String.valueOf(passwordEditText.getText());
        if(sqLiteManager.checkUserExist(email) == false){
            int id = sqLiteManager.getLastUserId()+1;
            User newUser = new User(id, email, pwd);
            sqLiteManager.addUserToDatabase(newUser);
            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("user_id", id);
            finish();
            startActivity(i);
        }else{
            int userFetch = sqLiteManager.login( email, pwd);
            if(userFetch != -1){
                User newUser = new User(userFetch, email, pwd);
                Intent i = new Intent(this, HomeActivity.class);
                i.putExtra("user_id", userFetch);
                finish();
                startActivity(i);
            }
        }

    }
}