package com.ninese.mysqlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final TodoList editTodoList = (TodoList) getIntent().getSerializableExtra("editTodoList");

        final EditText editText = (EditText)findViewById(R.id.textViewEdit);
        editText.setText(editTodoList.getTodoText());
        Button btn_edit = (Button)findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList eTodoList = new TodoList();
                eTodoList.setId(editTodoList.getId());
                eTodoList.setTodoText(String.valueOf(editText.getText()));
                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(eTodoList);
                todoListDAO.close();
                finish();
            }
        });

        Button btn_del = (Button)findViewById(R.id.btn_del);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListDAO todoListDAODel = new TodoListDAO(getApplicationContext());
                todoListDAODel.open();
                todoListDAODel.delete(editTodoList);
                todoListDAODel.close();
                finish();;

            }
        });

    }
    public boolean OnCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id== R.id.main_menu_add_new){
            Intent addNewIntent = new Intent (this,AddDataActivity.class);
            startActivity(addNewIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
