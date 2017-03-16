package com.ninese.mysqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by NineSE on 10/2/2560.
 */

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoListDAO(Context context) {
        dbHelper = new DbHelper(context);

    }
    public void open()
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public ArrayList<TodoList> getAllTodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM todo_List;",null);
        cursor.moveToFirst();
        TodoList todoList1;
        while (!cursor.isAfterLast()){
            todoList1 =  new TodoList();
            todoList1.setId(cursor.getInt(0));
            todoList1.setTodoText(cursor.getString(1));
            todoList.add(todoList1);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public void add_new(TodoList todoList)
    {
        TodoList newTodoList = new TodoList();
        newTodoList = todoList;

        ContentValues values = new ContentValues();
        values.put("todo_text",newTodoList.getTodoText());
        this.database.insert("todo_list",null,values);
        Log.d("Todo List Demo:::","ADD OK!!!");
        String insertData1 = "INSERT INTO todo_list (todo_text) VALUES ('Todo Text 1'); ";
    }
    public  void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values = new ContentValues();
        values.put("todo_text",updateTodoList.getTodoText());
        values.put("id",updateTodoList.getId());
        String where = "id="+updateTodoList.getId();
        this.database.update("todo_list",values,where,null);

        Log.d("Todo List Demo ::::","Update OK!!");
    }
    public  void delete(TodoList todoList){
        TodoList deleteTodoList = todoList;
        String sqlText = "DELETE FROM todo_list WHERE id = "+deleteTodoList.getId();
        this.database.execSQL(sqlText);

    }
}
