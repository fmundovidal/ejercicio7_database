package com.example.a5alumno.ejercicio7_database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveExtrnBtn = (Button) findViewById(R.id.btnExternalStorage);
        saveExtrnBtn.setOnClickListener(this);
        Button saveIntrnBtn = (Button) findViewById(R.id.btnInternalStorage);
        saveIntrnBtn.setOnClickListener(this);
        Button getFilesBtn = (Button) findViewById(R.id.btnGetFiles);
        getFilesBtn.setOnClickListener(this);
        Button dataBaseBtn = (Button) findViewById(R.id.btnDataBase);
        dataBaseBtn.setOnClickListener(this);
        Button okBtn = (Button) findViewById(R.id.btnOk);
        okBtn.setOnClickListener(this);

        TextView txtViewHello = (TextView) findViewById(R.id.textViewHello);

        SharedPreferences mSettings = this.getSharedPreferences(getString(R.string.my_shared_preferences), Context.MODE_PRIVATE);
        String mString = mSettings.getString("myStringKey","");
        txtViewHello.setText(mString);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EditText txtUpdate = (EditText) findViewById(R.id.edtTextUpdate);
        TextView txtViewHello = (TextView) findViewById(R.id.textViewHello);

       // SharedPreferences mSettings = this.getSharedPreferences(getString(R.string.my_shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences mSettings = this.getSharedPreferences(getString(R.string.my_shared_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSettings.edit();
        mEditor.putString("myStringKey", txtUpdate.getText().toString());
        mEditor.apply();

    }

    @Override
    public void onClick(View view) {
        EditText txtUpdate = (EditText) findViewById(R.id.edtTextUpdate);
        TextView txtViewHello = (TextView) findViewById(R.id.textViewHello);



        switch(view.getId()){
            case R.id.btnExternalStorage:
                File downloadFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"newDirectory/newSubDirectory");
                if(!downloadFolder.mkdirs())
                    Toast.makeText(this,"Directory not created", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Directory created", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnInternalStorage:
                try {
                    FileOutputStream mFileOutputStream = this.openFileOutput("internalStroageFile.txt",Context.MODE_APPEND);
                    String outputString = txtUpdate.getText().toString() + "\n";
                    mFileOutputStream.write(outputString.getBytes());
                    mFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnGetFiles:
                for (String aString : this.fileList() )
                {
                    Toast.makeText(this, aString, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDataBase:
                MyDatabase myDB = new MyDatabase(this);
                SQLiteDatabase s = myDB.getReadableDatabase();
               /* ContentValues values = new ContentValues();
                values.put(MyDatabase.KEY_WORD, "Ferran"); // Shop Name
                values.put(MyDatabase.KEY_DESCRIPTION, "Mundo"); // Shop Phone Number
                s.insert(MyDatabase.TABLE_NAME,null,values);
                s.close();*/
                break;
            case R.id.btnOk:
                txtViewHello.setText(txtUpdate.getText());
                break;
        }
    }

    public boolean isExternalStorageWritable(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    public boolean isExternalStorageReadable(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)||Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            return true;
        else
            return false;
    }
}

