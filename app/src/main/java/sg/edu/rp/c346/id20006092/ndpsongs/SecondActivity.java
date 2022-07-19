package sg.edu.rp.c346.id20006092.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> songsList;
    ArrayAdapter<Song> adapter;
    Button btn5Stars;

    ArrayList<String> years;
    Spinner spinner;


    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(this);
        songsList.clear();
        songsList.addAll(db.getAllSongs());
        adapter.notifyDataSetChanged();

        years.clear();
        years.addAll(db.getYears());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Show Song");

        lv = this.findViewById(R.id.lvsongs);
        btn5Stars = this.findViewById(R.id.btnShow5Stars);
        spinner = this.findViewById(R.id.spinner);

        DBHelper db = new DBHelper(this);
        songsList = db.getAllSongs();
        years = db.getYears();
        db.close();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("song", songsList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(SecondActivity.this);
                songsList.clear();
                songsList.addAll(db.getAllSongsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

    }
}