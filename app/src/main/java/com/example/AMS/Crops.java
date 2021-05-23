package com.example.AMS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Crops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops);

        Intent intent = getIntent();

        String districts = intent.getStringExtra("DISTRICTS");
        String season = intent.getStringExtra("SEASON");

        final String kharif[] = {"Rice", "Cotton", "Sugar Cane", "Maize", "Tomato", "Chilly"};
        final String rabi[] = {"Wheat", "Sugar Cane", "Potato", "Tomato", "Carrot", "Chilly"};
        final String zaid[] =  {"SugarCane", "Vegetable"};

        TextView dist = findViewById(R.id.district);
        TextView seas = findViewById(R.id.season);

        dist.setText(districts);
        seas.setText(season);

        ListView listView = findViewById(R.id.list);
        ArrayAdapter adapter;
        if (season.equals("Kharif(June-Sept)")){
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, kharif);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String crop = "" + kharif[position];//parent.getSelectedItem();
                    System.out.println(crop + "----------------+");

                    Intent intent1 = new Intent(Crops.this, Fertilizers.class);
                    intent1.putExtra("crop", crop);
                    startActivity(intent1);
                }
            });
        }
        else if (season.equals("Rabi(Nov-March)")){
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, rabi);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String crop = "" + rabi[position];//parent.getSelectedItem();
                    System.out.println(crop + "----------------+");

                    Intent intent1 = new Intent(Crops.this, Fertilizers.class);
                    intent1.putExtra("crop", crop);
                    startActivity(intent1);
                }
            });
        }
        else {
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, zaid);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String crop = "" + zaid[position];//parent.getSelectedItem();
                    System.out.println(crop + "----------------+");

                    Intent intent1 = new Intent(Crops.this, Fertilizers.class);
                    intent1.putExtra("crop", crop);
                    startActivity(intent1);
                }
            });
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String crop = "" + kharif[position];//parent.getSelectedItem();
                System.out.println(crop + "----------------+");

                Intent intent1 = new Intent(Crops.this, Fertilizers.class);
                intent1.putExtra("crop", crop);
                startActivity(intent1);
            }
        });

    }
}
