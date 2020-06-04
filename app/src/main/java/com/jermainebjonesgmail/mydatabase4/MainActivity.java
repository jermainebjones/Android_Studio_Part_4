package com.jermainebjonesgmail.mydatabase4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextId;
    Button buttonAddFruit;
    Spinner spinnerPrice;

    DatabaseReference databaseFruit;

    ListView listViewFruits;

    List<Fruit> fruitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseFruit = FirebaseDatabase.getInstance().getReference("fruits");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddFruit = (Button) findViewById(R.id.buttonAddFruit);
        spinnerPrice = (Spinner) findViewById(R.id.spinnerPrice);
        editTextId = (EditText) findViewById(R.id.editTextId);

        listViewFruits = (ListView) findViewById(R.id.listViewFruits);

        fruitList = new ArrayList<>();

        buttonAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFruit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseFruit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fruitList.clear();

                for (DataSnapshot fruitSnapshot: dataSnapshot.getChildren()){
                    Fruit fruit = fruitSnapshot.getValue(Fruit.class);

                    fruitList.add(fruit);
                }

                FruitList adapter = new FruitList(MainActivity.this, fruitList);
                listViewFruits.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addFruit() {
        String name = editTextName.getText().toString().trim();
        String price = spinnerPrice.getSelectedItem().toString();
        String labelid = editTextId.getText().toString().trim();

        if (!TextUtils.isEmpty(name)){

            String id = databaseFruit.push().getKey();

            Fruit fruit = new Fruit(id, name, price, labelid);

            databaseFruit.child(id).setValue(fruit);

            Toast.makeText(this, "Fruit added", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Please enter a fruit", Toast.LENGTH_LONG).show();
        }
    }
}
