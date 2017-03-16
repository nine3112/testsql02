package com.ninese.mysqlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView todoListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.main_menu_add_new){
            Intent addNewIntent = new Intent (this,AddDataActivity.class);
            startActivity(addNewIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override protected void onResume() { super.onResume();
        todoListView = (ListView) findViewById(R.id.todo_listView);

        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();
        ArrayList<TodoList> myList = todoListDAO.getAllTodoList();
  //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        final MyListView adapter = new MyListView(this,myList);
        todoListView.setAdapter(adapter);
        todoListDAO.close();
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)),
                   //     Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(),EditActivity.class);
                editIntent.putExtra("editTodoList", (Serializable) adapter.getItem(position));
                startActivity(editIntent);

            }
        });

    }
    }
