package almirakhonsa.coursehelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import almirakhonsa.coursehelper.data.registerContract;
import almirakhonsa.coursehelper.data.registerHelper;

public class Register extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish_list);

        nameEditText = (EditText) findViewById(R.id.etName);
        emailEditText = (EditText) findViewById(R.id.etEmailAddress);
        passEditText = (EditText) findViewById(R.id.etPassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String pass = passEditText.getText().toString();

                if (!name.isEmpty() || !email.isEmpty() || !pass.isEmpty()) {
                    insertData();
                }
            }
        });
    }
    private void insertData(){
        String nameString = nameEditText.getText().toString().trim();
        String emailString = emailEditText.getText().toString().trim();
        String passString = passEditText.getText().toString().trim();

        registerHelper dbHelper = new registerHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(registerContract.regEntry.COLUMN_NAME, nameString);
        values.put(registerContract.regEntry.COLUMN_EMAIL, emailString);
        values.put(registerContract.regEntry.COLUMN_PASS, passString);

        long newRowID =  db.insert(registerContract.regEntry.TABLE_NAME, null, values);
        if (newRowID == -1) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}
