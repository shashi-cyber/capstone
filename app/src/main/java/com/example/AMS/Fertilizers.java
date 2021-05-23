package com.example.AMS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Fertilizers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizers);

        Intent intent = getIntent();
        String crops = intent.getStringExtra("crop");

        System.out.println(crops + "----------------");

        TextView nitrogen = findViewById(R.id.nitro);
        TextView phos = findViewById(R.id.phos);
        TextView potas = findViewById(R.id.potas);

        ListView listView = findViewById(R.id.microList);

        String riceMacro[] = {"80-100 kg/hectare" , "60-80 kg/hectare" , "30-45 kg/hectare"};
        String riceMicro[] = {"Boron", "Copper", "Iron", "Manganese", "Zinc", "Nickel", "Chlorine", "Molybdenum"};

        String cottonMacro[] = {"20-40 kg/hectare","20 kg/hectare", "20 kg/hectare"};
        String cottonMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String wheatMacro[] = {"120 kg/hectare","60 kg/hectare", "40 kg/hectare"};
        String wheatMicro[] = {"Boron", "Copper", "Iron", "Manganese", "Zinc", "Molybdenum", "Chlorine"};

        String sugarMacro[] = {"250 kg/hectare","100 kg/hectare", "120 kg/hectare"};
        String sugarMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String maizeMacro[] = {"120-150 kg/hectare","60-75 kg/hectare", "35 kg/hectare"};
        String maizeMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String potatoMacro[] = {"120 kg/hectare","240 kg/hectare", "120 kg/hectare"};
        String potatoMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String carrotMacro[] = {"135 kg/hectare","135 kg/hectare", "135 kg/hectare"};
        String carrotMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String tomatoMacro[] = {"50 kg/hectare","150 kg/hectare", "150 kg/hectare"};
        String tomatoMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        String chillyMacro[] = {"60 kg/hectare","80 kg/hectare", "80 kg/hectare"};
        String chillyMicro[] = {"Boron", "Copper", "Iron", "Zinc", "Molybdenum", "Chlorine"};

        ArrayAdapter adapter;

        if (crops.equals("Rice")){
            nitrogen.setText(riceMacro[0]);
            phos.setText(riceMacro[1]);
            potas.setText(riceMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, riceMicro);
        }
        else if (crops.equals("Cotton")){
            nitrogen.setText(cottonMacro[0]);
            phos.setText(cottonMacro[1]);
            potas.setText(cottonMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, cottonMicro);

        }
        else if (crops.equals("Wheat")){
            nitrogen.setText(wheatMacro[0]);
            phos.setText(wheatMacro[1]);
            potas.setText(wheatMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, wheatMicro);

        }
        else if (crops.equals("Sugar Cane")){
            nitrogen.setText(sugarMacro[0]);
            phos.setText(sugarMacro[1]);
            potas.setText(sugarMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, sugarMicro);

        }
        else if (crops.equals("Maize")){
            nitrogen.setText(maizeMacro[0]);
            phos.setText(maizeMacro[1]);
            potas.setText(maizeMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, maizeMicro);

        }
        else if (crops.equals("Potato")){
            nitrogen.setText(potatoMacro[0]);
            phos.setText(potatoMacro[1]);
            potas.setText(potatoMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, potatoMicro);

        }
        else if (crops.equals("Carrot")){
            nitrogen.setText(carrotMacro[0]);
            phos.setText(carrotMacro[1]);
            potas.setText(carrotMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, carrotMicro);

        }
        else if (crops.equals("Tomato")){
            nitrogen.setText(tomatoMacro[0]);
            phos.setText(tomatoMacro[1]);
            potas.setText(tomatoMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, tomatoMicro);

        }
        else {
            nitrogen.setText(chillyMacro[0]);
            phos.setText(chillyMacro[1]);
            potas.setText(chillyMacro[2]);

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, chillyMicro);
        }

        listView.setAdapter(adapter);
    }
}
