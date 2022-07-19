package sg.edu.rp.c346.id20006092.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle, etSingers, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + "Modify Song");

        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);
        etId = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextSongTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");

        etId.setText(currentSong.getId() + "");
        etTitle.setText(currentSong.getTitle() + "");
        etSingers.setText(currentSong.getSingers() + "");

        switch (currentSong.getStars()) {
            case 5: rb5.setChecked(true);
                break;
            case 4: rb4.setChecked(true);
                break;
            case 3: rb3.setChecked(true);
                break;
            case 2: rb2.setChecked(true);
                break;
            case 1: rb1.setChecked(true);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                currentSong.setTitle(etTitle.getText().toString());
                currentSong.setSingers(etSingers.getText().toString());
                int year = 0;
                currentSong.setYear(year);
                int selectedRating  = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedRating);
                currentSong.setStars((Integer.parseInt(rb.getText().toString())));
                int result = db.UpdateSong(currentSong);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                int result = db.deleteSong(currentSong.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}