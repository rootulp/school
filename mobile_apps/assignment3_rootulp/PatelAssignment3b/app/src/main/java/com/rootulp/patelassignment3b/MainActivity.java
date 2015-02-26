package com.rootulp.patelassignment3b;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    int curr;
    EditText userInput;
    Button addEntry;
    Button updateEntry;
    Button deleteEntry;
    Button saveList;
    Button closeApp;
    ListView todoList;
    ArrayList<String> todoArray = new ArrayList<String>();
    FileOutputStream outputStream;
    FileInputStream inputStream;
    String file = "output file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            inputStream = openFileInput(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            todoArray = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        todoList = (ListView)findViewById(R.id.todos);
        addEntry = (Button)findViewById(R.id.addEntry);
        updateEntry = (Button)findViewById(R.id.updateEntry);
        deleteEntry = (Button)findViewById(R.id.deleteEntry);
        saveList = (Button)findViewById(R.id.saveList);
        closeApp = (Button)findViewById(R.id.closeApp);
        userInput = (EditText)findViewById(R.id.input);
        userInput.setText("");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, todoArray);
        todoList.setAdapter(adapter);
        todoList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                userInput.setText(todoArray.get(position));
                curr = position;
            }
        });

        // add new to-do
        addEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                todoArray.add(userInput.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        // update current to-do
        updateEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curr == todoArray.size()) {
                    return;
                } else {
                    todoArray.set(curr, userInput.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // delete current to-do
        deleteEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curr == todoArray.size()) {
                    return;
                } else {
                    todoArray.remove(curr);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // save all todos
        saveList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curr == todoArray.size()) {
                    return;
                } else {
                    try {
                        outputStream = openFileOutput(file, Context.MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(todoArray);
                        objectOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // exit app
        closeApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

