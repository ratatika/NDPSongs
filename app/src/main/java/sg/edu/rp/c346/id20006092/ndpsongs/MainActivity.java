package sg.edu.rp.c346.id20006092.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + "Insert Song");

        etTitle = findViewById(R.id.editTextSongTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.buttonUpdate);
        btnShowList = findViewById(R.id.buttonDelete);
        rg = findViewById(R.id.radioGroup);

        btnInsert.setOnClickListener((view -> {

            String title = etTitle.getText().toString().trim();
            String singers = etSingers.getText().toString().trim();

            if (title.length() == 0 || singers.length() == 0) {
                Toast.makeText(MainActivity.this, "Data is incomplete!", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = 0;

            DBHelper dbh = new DBHelper(MainActivity.this);

            int stars = getStars();
            dbh.insertSong(title, singers, year, stars);
            dbh.close();
            Toast.makeText(MainActivity.this, "Song Inserted!", Toast.LENGTH_LONG).show();

            etTitle.setText("");
            etSingers.setText("");
            etYear.setText("");

        }));

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });


    }

    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButton1:
                stars = 1;
                break;
            case R.id.radioButton2:
                stars = 2;
                break;
            case R.id.radioButton3:
                stars = 3;
                break;
            case R.id.radioButton4:
                stars = 4;
                break;
            case R.id.radioButton5:
                stars = 5;
                break;
        }
        return stars;
    }
}