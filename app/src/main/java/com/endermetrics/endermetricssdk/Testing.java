package com.endermetrics.endermetricssdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.endermetrics.endermetricslibrary.Child;
import com.endermetrics.endermetricslibrary.Endermetrics;
import com.endermetrics.endermetricslibrary.Report;

import java.util.ArrayList;


public class Testing extends AppCompatActivity {

    private Endermetrics EM = new Endermetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Log.d("TAG", "Loading MainActivity");


        ////////////////////
        //ACCOUNT REGISTER//
        ////////////////////

        Button reg_account = (Button) findViewById(R.id.account_register);

        reg_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button register account");
                EM.init("2f3524d964c40282819b3e8f011f55d9");
                EM.account_register("Android_t", new Endermetrics.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Account Register --> ", result);
                    }
                });

            }
        });


        //////////////////
        //GET ACCOUNT ID//
        //////////////////

        Button get_account_id = (Button) findViewById(R.id.get_id);

        get_account_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button get account id");
                EM.get_id("Android_trial", new Endermetrics.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Account id --> ", result);
                    }
                });
            }
        });


        ////////
        //AUTH//
        ////////

        Button auth = (Button) findViewById(R.id.auth);

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button auth");
                EM.init("2f3524d964c40282819b3e8f011f55d9");
                EM.auth("3a45a48340346c503254c0199913b7bc", "c28d800876e8c103c3dc260cd5c009ec", new Endermetrics.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Auth --> ", result);
                    }
                });
            }
        });

        //////////////////
        //CHILD REGISTER//
        //////////////////

        Button reg_child = (Button) findViewById(R.id.child_register);

        reg_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button register child");
                EM.child_register("Android_trial", "01/01/2005", "MALE", "3a45a48340346c503254c0199913b7bc", new Endermetrics.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("Child id --> ", result);
                    }
                });
            }
        });

        /////////////
        //GET CHILD//
        /////////////

        Button get_child = (Button) findViewById(R.id.get_child);

        get_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button get child");
                Log.d("TAG", EM.get_child());
            }
        });

        ////////////////////
        //GET ALL CHILDREN//
        ////////////////////

        Button get_all = (Button) findViewById(R.id.get_all);

        get_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button get child");
                EM.get_all("3a45a48340346c503254c0199913b7bc", new Endermetrics.CallbackChildList() {
                    @Override
                    public void onSuccess(ArrayList <Child>result) {
                        Log.d("Get all children -->", result.toString());
                    }
                });
            }
        });


        ////////////
        //INIT SET//
        ////////////

        Button init_set = (Button) findViewById(R.id.init_set);

        init_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button init set");
                EM.init_set("fe187941f94313501401394d2aca2a5b", EM.getSession_token(), 2);
            }
        });

        /////////////
        //TRACK SET//
        /////////////

        Button track_set = (Button) findViewById(R.id.track_set);

        track_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button track set");

                EM.init_set("fe187941f94313501401394d2aca2a5b", EM.getSession_token(), 2);

                EM.add_hit("e7b656b8df1376d208028bd2594a4c67", "SUCCESS");
                EM.add_hit("e7b656b8df1376d208028bd2594a4c67","SUCCESS");
                EM.add_hit("e7b656b8df1376d208028bd2594a4c67", "FAIL");
                EM.track_set("FAILED", new Endermetrics.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        if(result == "200"){
                            Log.d("TRACK SET -->", "Set tracked.");
                        }
                    }
                });
            }
        });


        ////////////////
        //CHILD REPORT//
        ////////////////

        Button child_report = (Button) findViewById(R.id.child_report);

        child_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Button child report");
                EM.init("2f3524d964c40282819b3e8f011f55d9");
                EM.child_report("3a45a48340346c503254c0199913b7bc", "c28d800876e8c103c3dc260cd5c009ec", new Endermetrics.CallbackReport(){
                    @Override
                    public void onSuccess(ArrayList <Report> result) {
                        Log.d("Child Report --> ", result.toString());
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_testing, menu);
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
