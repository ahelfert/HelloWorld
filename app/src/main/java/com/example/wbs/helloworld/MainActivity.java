package com.example.wbs.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button bttn;
    private EditText eingabe;
    private ListView anrede;
    private ArrayAdapter<String> adapter;
    private boolean firstClick = true;

    private String ar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.nachricht);
        bttn = (Button) findViewById(R.id.weiter);
        eingabe = (EditText) findViewById(R.id.eingabe);
        anrede = (ListView) findViewById(R.id.anrede);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.anreden));
        // verbindet Daten aus strings.xml mit dem Layout activity_main.xml
        anrede.setAdapter(adapter);
        anrede.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ar = adapter.getItem(i) + " ";

                // markiere selektierten Eintrag (aktuell in activity_main.xml realisiert
                // anrede.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                // anrede.setSelector(android.R.color.holo_blue_light);

                // alternativ
                // anrede.getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            }
        });
        text.setText(R.string.nenneName);
        bttn.setText(R.string.btntxt);
        bttn.setEnabled(false);
        eingabe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bttn.setEnabled(editable.length() > 0);
            }
        });
        bttn.setEnabled(false);
        bttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (firstClick) {
                    text.setText(getString(R.string.hallo, ar + eingabe.getText()));
                    eingabe.setVisibility(View.INVISIBLE);
                    anrede.setVisibility(View.INVISIBLE);
                    firstClick = false;
                } else finish();
            }
        });


    }
}
