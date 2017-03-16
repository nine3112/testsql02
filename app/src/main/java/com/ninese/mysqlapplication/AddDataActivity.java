package com.ninese.mysqlapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Button Add_new = (Button) findViewById(R.id.btn_add_new);
        final EditText editText = (EditText) findViewById(R.id.edit_add_new);

       Add_new.setOnClickListener(new View.OnClickListener(){


           @Override
           public void onClick(View v) {
                TodoList todoList = new TodoList();
               todoList.setTodoText(String.valueOf(editText.getText()));
               TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
               todoListDAO.open();
               todoListDAO.add_new(todoList);
               todoListDAO.close();
               finish();

           }
       });

    }
}
