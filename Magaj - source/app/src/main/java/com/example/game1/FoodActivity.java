package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    ListView list;
    TextView text_back;
    String[] itemname ={
            "Oily fish",
            "Dark chocolate",
            "Berries",
            "Nuts and seeds",
            "Whole grains",
            "Coffee",
            "Avocados",
            "Peanuts",
            "Eggs",
            "Broccoli",
            "Kale",
            "Soy products"
    };

    Integer[] imgid={
            R.drawable.oily_fish,
            R.drawable.chocolate,
            R.drawable.berries,
            R.drawable.nuts,
            R.drawable.whole_grain,
            R.drawable.coffee_beans,
            R.drawable.avocado,
            R.drawable.peanuts,
            R.drawable.egg,
            R.drawable.broccali,
            R.drawable.kale,
            R.drawable.soybeans,
    };

    String[] itemdesc ={
            "Oily fish are a good source of omega-3 fatty acids. Omega-3s help build membranes around each cell in the body, including the brain cells",
            "Cacao contains flavonoids, a type of antioxidant. Antioxidants are especially important for brain health.",
            "Many berries contain flavonoid antioxidants. Research suggests that these may make the berries good food for the brain.",
            "Nuts and seeds are also rich sources of the antioxidant vitamin E, which protects cells from oxidative stress caused by free radicals.",
            "Eating whole grains is another way to benefit from the effects of vitamin E, with these grains being a good source of the vitamin.",
            "Apart from caffine, Coffee is also a source of antioxidants, which may support brain health as a person gets older",
            "A source of healthful unsaturated fat, avocados may support the brain. Eating monounsaturated fats may reduceTrusted Source blood pressure, and high blood pressure is linked with cognitive decline.",
            "Peanuts also provide key vitamins and minerals to keep the brain healthy, including high levels of vitamin E and resveratrol.",
            "eggs can be an effective brain food. They are a good source of the following B vitamins: vitamin B-6, vitamin B-12, folic acid",
            "Broccoli is rich in compounds called glucosinolates. When the body breaks these down, they produce isothiocyanates which helps in reducing oxidation stress.",
            "Like broccoli, kale contains glucosinolates, and leafy greens also contain other key antioxidants, vitamins, and minerals.",
            "Soybean products are rich in a particular group of antioxidants called polyphenols. which can improve cognitive abilities"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getSupportActionBar().setTitle("Food That are Good for You!");

        text_back = findViewById(R.id.text_back);
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid,itemdesc);
        list=(ListView)findViewById(R.id.food_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem= itemname[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        text_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onBackPressed(){
        finish();
    }
}
