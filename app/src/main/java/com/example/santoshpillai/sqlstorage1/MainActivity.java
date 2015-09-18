package com.example.santoshpillai.sqlstorage1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

// This is a git test
// This is a git test 2

public class MainActivity extends ActionBarActivity {

    MyDBAdapter adapter;

    EditText userName, password, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.text1);
        password = (EditText) findViewById(R.id.text2);
        name = (EditText) findViewById(R.id.text3);

        adapter= new MyDBAdapter(this);


    }


    public void doInsert(View v){
        String user = userName.getText().toString();
        String pass = password.getText().toString();

       long id=  adapter.insertData(user, pass);
       if(id<0){
           Message.messaage(this,"Data Not Saved");
       }
        else {
           Message.messaage(this,"Data Saved Successfully!");
       }



    }

    public void viewDetails(View v){

       // Message.messaage(this, "ViewDetails was called successfully");

        String data = adapter.allData();

        if(data.length()>0){
            Message.messaage(this, data);
        }
        else{
            Message.messaage(this, "Table is empty");
        }


    }

      public void specificDetails (View v){

        String myname=  name.getText().toString();

         if(myname.length()>0) {
             String s1 = adapter.getData(myname);
             if(s1.length()>0)
               Message.messaage(this, s1);
                 else
                   Message.messaage(this, "No details found for " +myname);
         }

         else {
             Message.messaage(this, "Enter a name first");
         }
      }

    public void myID(View v){
        String myname = name.getText().toString();
        if (myname.length()>0){
            //String name1 = myname.substring(0, myname.indexOf(" "));
            //String name2 = myname.substring(myname.indexOf("")+1);

            int ID = adapter.getID(myname);
            if(ID >= 0)
              Message.messaage(this,  Integer.toString(ID));
                else
                   Message.messaage(this,  "No details found for " +myname);
        }

        else{
            Message.messaage(this, "Enter a name first");
        }



    }

    public void toUpdate(View v){
        if(name.getText().toString().length()>0) {
            String text = name.getText().toString();
            int id = Integer.parseInt(text);

            adapter.updateIt(id);
            Message.messaage(this, "Successfully updated Name with id = " + id);

        }

        else{
            Message.messaage(this, "Enter a number first");
        }


    }

    public void toDelete(View v){
        if(name.getText().toString().length()>0){
            int id = Integer.parseInt(name.getText().toString());
            adapter.deleteIt(id);
            Message.messaage(this, "Successfully deleted Name with id = " + id);
        }

        else{
            Message.messaage(this, "Enter a number first");
        }
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
