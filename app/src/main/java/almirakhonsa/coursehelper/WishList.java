package almirakhonsa.coursehelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import almirakhonsa.coursehelper.data.wishContract.wishEntry;
import almirakhonsa.coursehelper.data.wishHelper;

public class WishList extends AppCompatActivity {

    private wishHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishList.this, EditWishList.class);
                startActivity(intent);
            }
        });

        dbHelper = new wishHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo(){
        wishHelper dbHelper = new wishHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                wishEntry.COLUMN_NAME,
                wishEntry.COLUMN_UNIV,
                wishEntry.COLUMN_CITY,
                wishEntry.COLUMN_COURSE,
        };
        Cursor cursor = db.query(wishEntry.TABLE_NAME, projection,null,null,null,null,null);

        TextView displayView = (TextView) findViewById(R.id.text_view_wish);
        try {
            displayView.setText("Your Coming Up Event is " + cursor.getCount() + " : \n\n");
            int idColumnIndex = cursor.getColumnIndex(wishEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(wishEntry.COLUMN_NAME);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                int i = 1;
                displayView.append("\n"+ i + ". " + "Name of Course : " + cursor.getString(3));
                cursor.moveToNext();
                i = i+1;
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wish, menu);
        return true;
    }

    public void deletedb() {
        wishHelper dbHelper = new wishHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(wishEntry.TABLE_NAME, null, null);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        displayDatabaseInfo();
        switch (item.getItemId()) {
            case R.id.action_delete_all_entries:
                deletedb();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}