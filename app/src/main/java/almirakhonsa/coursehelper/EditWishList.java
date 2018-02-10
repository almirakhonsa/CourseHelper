package almirakhonsa.coursehelper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import almirakhonsa.coursehelper.data.wishContract.wishEntry;
import almirakhonsa.coursehelper.data.wishHelper;

public class EditWishList extends AppCompatActivity {

    private EditText nameEditText;
    private EditText univEditText;
    private EditText cityEditText;
    private Spinner courseSpinner;

    private String course = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish_list);

        nameEditText = (EditText) findViewById(R.id.edit_my_name);
        univEditText = (EditText) findViewById(R.id.edit_my_univ);
        cityEditText = (EditText) findViewById(R.id.edit_my_city);
        courseSpinner = (Spinner) findViewById(R.id.spinner_course);

        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_course_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        courseSpinner.setAdapter(genderSpinnerAdapter);
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.c_lia))) {
                        course = wishEntry.COURSE_LIA;
                    } else if (selection.equals(getString(R.string.c_ef))) {
                        course = wishEntry.COURSE_EF;
                    } else if (selection.equals(getString(R.string.c_go))) {
                        course = wishEntry.COURSE_GO;
                    } else if (selection.equals(getString(R.string.c_neutron))) {
                        course = wishEntry.COURSE_NEUTRON;
                    } else {
                        course = "Unknown"; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                course = "Unknown"; // Unknown
            }
        });
    }

    private void insertData(){
        String nameString = nameEditText.getText().toString().trim();
        String univString = univEditText.getText().toString().trim();
        String cityString = cityEditText.getText().toString().trim();

        wishHelper dbHelper = new wishHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(wishEntry.COLUMN_NAME, nameString);
        values.put(wishEntry.COLUMN_UNIV, univString);
        values.put(wishEntry.COLUMN_COURSE, course);
        values.put(wishEntry.COLUMN_CITY, cityString);

        long newRowID =  db.insert(wishEntry.TABLE_NAME, null, values);
        if (newRowID == -1) {
            Toast.makeText(this, "Error When Saving Wish List", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wish List Saved With id : " + newRowID, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.action_save:
                insertData();
                finish();
                return true;
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
