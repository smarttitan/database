package com.example.database;

// Database Imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

// Server Imports
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class account1 extends AppCompatActivity{

    // United Variables
    EditText editEmail, editPassword, editName;
    Button btnSignIn, btnRegister;

    // Database Variables
    protected DBManager dbManager;

    // Server Variables
    String URL= "http://192.168.56.1/test_android/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Method Meta-Data
        super.onCreate(savedInstanceState);

        setTitle("Add Account");

        setContentView(R.layout.activity_account1);

        // United Initiations
        editName = (EditText) findViewById(R.id.editName);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        // Server Initiations
        editEmail=(EditText)findViewById(R.id.editEmail);
        editPassword=(EditText)findViewById(R.id.editPassword);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        // Database Initiations
        dbManager = new DBManager(this);

        // United Code
        // On Click Button
       btnSignIn.setOnClickListener(new OnClickListener() {

           @Override
           // SignIn Button
           public void onClick(View view) {

               // Database Code
               // Open Database
               dbManager.open();

               // Insert Data to Database
               final String add = editName.getText().toString();
               dbManager.insert(add);

               // Fetch Data to Log
               dbManager.fetch();

               // Close Database
               dbManager.close();

               // Server Code
               // LogIn Code
               AttemptLogin attemptLogin = new AttemptLogin();
               attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),"");
           }
       });

        // Server Code
        // Register Button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Buttons Visibility Code
                if(i==0)
                {
                    i=1;
                    editEmail.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    btnRegister.setText("CREATE ACCOUNT");
                }
                else{

                    btnRegister.setText("REGISTER");
                    editEmail.setVisibility(View.GONE);
                    btnSignIn.setVisibility(View.VISIBLE);
                    i=0;

                    // Register Code
                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),editEmail.getText().toString());

                }

            }
        });
    }

    // Server Code
    // LogIn Code
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override
        // Before Execution
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        // Server Background Tasks
        protected JSONObject doInBackground(String... args) {


            String email = args[2];
            String password = args[1];
            String name= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            // Email Conditioning
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));
            // Http Request Code
            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        // After Execution
        protected void onPostExecute(JSONObject result) {

            // Process Result
            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}