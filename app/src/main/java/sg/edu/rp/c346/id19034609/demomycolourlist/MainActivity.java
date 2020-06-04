package sg.edu.rp.c346.id19034609.demomycolourlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement, etPosition;
    Button btnAdd, btnRemove, btnUpdate;
    ListView lvColour;
    ArrayList<String> alColours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextColour);
        etPosition = findViewById(R.id.editTextIndex);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnUpdate= findViewById(R.id.buttonUpdateItem);
        lvColour = findViewById(R.id.listViewColour);

        alColours = new ArrayList<String>();
        alColours.add("Red");
        alColours.add("Orange");

        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alColours);
        lvColour.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colourName = etElement.getText().toString();
                if (etElement.getText().toString().trim().length() != 0) {
                    if (etPosition.getText().toString().trim().length() != 0) {
                        int pos = Integer.parseInt(etPosition.getText().toString());
                        if (pos < alColours.size()) {
                            alColours.add(pos, colourName);
                            adapter.notifyDataSetChanged();
                            reset();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        alColours.add(colourName);
                        adapter.notifyDataSetChanged();
                        reset();
                    }
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPosition.getText().toString().trim().length() != 0) {
                    int pos = Integer.parseInt(etPosition.getText().toString());
                    if (pos < alColours.size()) {
                        alColours.remove(pos);
                        adapter.notifyDataSetChanged();
                        reset();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etElement.getText().toString().trim().length() != 0 && etPosition.getText().toString().trim().length() != 0) {
                    String colourName = etElement.getText().toString();
                    int pos = Integer.parseInt(etPosition.getText().toString());
                    if (pos < alColours.size()) {
                        alColours.set(pos, colourName);
                        adapter.notifyDataSetChanged();
                        reset();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = alColours.get(position);
                Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void reset() {
        etElement.setText("");
        etPosition.setText("");
    }
}
