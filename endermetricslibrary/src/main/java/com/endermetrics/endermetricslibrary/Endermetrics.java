package com.endermetrics.endermetricslibrary;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;

/**
 * Created by Sergi on 19/10/15.
 */


public class Endermetrics extends AppCompatActivity{


    //VARS
    private String urlEnder = "http://api.endermetrics.com/";
    private String version = "v1";
    private String app_token ="";
    private String child_id = "";
    private String account_id = "";
    private String session_token = "";
    private ArrayList<Child> childrenList = new ArrayList<Child>();
    private ArrayList<Report> reportList = new ArrayList<>();
    private Set set = new Set();


    //This interface controls the callback call when return Strings.
    public interface Callback{
        void onSuccess(String result);
    }

    //This interface controls the callback call that return Arraylists.
    public interface CallbackChildList{
        void onSuccess(ArrayList <Child>result);
    }

    public interface CallbackReport{
        void onSuccess(ArrayList <Report>result);
    }


    public String getSession_token() {
        return session_token;
    }


    //ACCOUNT//
    /*INIT TOKEN
    * This method iniciates the application with its token. */
    public void init (String token){
        app_token=token;
    }


    /*ACCOUNT REGISTER.
    * This function will add a new account to the EnderMetrics server. You'll add a custom id to the
    id and the method will return an account id for this account.*/
    public String account_register(final String custom_id, final Callback callback){

        String tag_string_req = "string_req";
        final String url = urlEnder+version+"/account/register";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse (String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "Correct.");
                        JSONObject acc = new JSONObject(json.getString("data"));
                        account_id = acc.getString("account_id");
                        Log.d("Account id -->", account_id);
                        callback.onSuccess(account_id);
                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"app_token\":\""+app_token+"\", \"custom_id\":\""+custom_id+"\"}");
                params.put("format", "json");

                //Log.d("TAG", params.toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return account_id;
    }

    /*GET ID
    * This method will return the account_id if the custom_id exists in our Endermetrics database.*/
    public String get_id(final String custom_id, final Callback callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/account/getid";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "It works!");
                        JSONObject acc = new JSONObject(json.getString("data"));
                        account_id = acc.getString("account_id");
                        Log.d("Account id -->", account_id);
                        callback.onSuccess(account_id);
                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        account_id = null;
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"custom_id\":\""+custom_id+"\"}");
                params.put("format", "json");

                Log.d("Map get_id: ", params.toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return account_id;

    }

    /*AUTH
    * At the start of the application you'll need to generate a token to do request to the API.
    * You'll need an account id (aid) and a child id (cid). If the result is correct the method will
    * return a token. */
    public String auth(final String aid, final String cid, final Callback callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/auth/token";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "It works!");
                        //JSONObject acc = new JSONObject(json.getString("data"));
                        session_token = json.getString("session_token");
                        Log.d("Session token -->", session_token);
                        callback.onSuccess(session_token);

                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        session_token = null;
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"app_token\":\""+app_token+"\",\"account_id\":\""+aid+"\", \"child_id\":\""+cid+"\"}");
                params.put("format", "json");

                Log.d("TAG", params.toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().getRequestQueue();
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return session_token;
    }


    //CHILD//
    /*With this function you'll add une or more children in your application. To create a child
    * you'll need a nick, birthdate, gender and an account id (aid). If the return is correct, the
    * method will return a child id.*/
    public String child_register(final String nick, final String birthdate, final String gender, final String aid, final Callback callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/child/register/";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "Correct.");
                        JSONObject ch = new JSONObject(json.getString("data"));
                        child_id = ch.getString("child_id");
                        Log.d("Child id -->", child_id);
                        callback.onSuccess(child_id);

                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        child_id = null;
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"nick\":\""+nick+"\",\"birthdate\":\""+birthdate+"\",\"gender\":\""+gender+"\",\"account_id\":\""+aid+"\"}");
                params.put("format", "json");

                //Log.d("TAG", params.toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return child_id;
    }

    /*This method will return the last child created in this session.*/
    public String get_child(){
        Log.d("Get child -->", child_id);
        return child_id;
    }

    /*GET ALL
    With this method you can get a list of children of an account id. To get the list, you need an
    account id (aid). If the account exists, the method will return an ArrayList of children.*/
    public ArrayList get_all(final String aid, final CallbackChildList callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/child/getall/";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "Correct.");
                        JSONObject data = new JSONObject(json.getString("data"));
                        JSONArray jsonlist = new JSONObject(data.toString()).getJSONArray("list");

                        Log.d("JSONList -->", jsonlist.toString());

                        for (int i=0; i<jsonlist.length(); i++){
                            JSONObject js = jsonlist.getJSONObject(i);
                            String id = js.getString("id");
                            String account_id = js.getString("account_id");
                            String nick = js.getString("nick");
                            String birthdate = js.getString("birthdate");
                            String gender = js.getString("gender");
                            String custom_id = js.getString("custom_id");

                            Child jsonChild = new Child(id,account_id,nick,birthdate,gender,custom_id);
                            childrenList.add(i, jsonChild);
                        }
                        callback.onSuccess(childrenList);

                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"account_id\":\""+aid+"\"}");
                params.put("format", "json");

                //Log.d("TAG", params.toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return childrenList;
    }

    //SET//

    /*When you start a game you have to iniciate a set. Doing this we will know the child, the game
     and the level the child is playing right now. We'll need and activity_token, the session_token
     and the level.*/
    public void init_set(String activity_token, String session_token, int level) {
        this.session_token=session_token;
        set.initSet(activity_token, level);
    }

    /*This method will add a hit to the actual set. You'l just need the skill token obtained from
    * the web and the result.*/
    public void add_hit(String skill_token, String result){

        set.addHit(skill_token, result);
    }

    /*TRACK SET
    When the game it's over, using this function will save the results to the API of EnderMetrics.
    we'll just need to add the result of the set to save the set.
     */
    public void track_set(final String result, final Callback callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/track/set";
        set.end(result);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "Set tracked.");
                        callback.onSuccess("200");
                    }
                    else{
                        Log.d("code = 400", "Error connecting to server.");
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"session_token\":\""+session_token+"\",\"data\":{\"activity_token\":\""+set.getActivityToken()+"\",\"level\":\""+set.getLevel()+"\",\"time\":\""+set.getTime()+"\",\"result\":\""+set.getResult()+"\","+set.toJson()+"}}");
                params.put("format", "json");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }



    //CHILD REPORT//
    /*
    There are moments that you need to use the information saved from a child in EnderMetrics.
    To obtain the information use this function, and you'll need the account id and the child id
    of the child. If the result it's ok you'll get an ArrayList with all the information tracked.

     */

    public ArrayList child_report(final String account_id, final String child_id, final CallbackReport callback){

        String tag_string_req = "string_req";
        String url = urlEnder+version+"/child/report/";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject code = new JSONObject(json.getString("meta"));
                    String c = code.getString("code");
                    Log.d("JSON -->", c.toString());
                    if (c.equals("200")){
                        Log.d("code = 200", "Report child.");
                        Report r = new Report();
                        JSONObject data = new JSONObject(json.getString("data"));
                        JSONArray jsonlist = new JSONObject(data.toString()).getJSONArray("activities");

                        for (int i=0; i<jsonlist.length();i++){
                            JSONObject js = jsonlist.getJSONObject(i);
                            String token = js.getString("token");
                            String name = js.getString("name");
                            String last_level = js.getString("last_level");
                            String max_level = js.getString("max_level");
                            String avg_time_session = js.getString("avg_time_session");
                            String total_time = js.getString("total_time");
                            String success_ratio = js.getString("success_ratio");

                            JSONArray jsonSkills = new JSONObject(js.toString()).getJSONArray("skills");

                            ArrayList <Skill>sk = new ArrayList();

                            for(int j=0;j<jsonSkills.length();j++){
                                JSONObject jsSkill = jsonSkills.getJSONObject(j);
                                String skillToken = jsSkill.getString("token");
                                String skillName = jsSkill.getString("name");
                                float ratio = Float.parseFloat(jsSkill.getString("ratio"));
                                int total_results = Integer.parseInt(jsSkill.getString("total_results"));

                                Skill s = new Skill(skillToken,skillName,ratio,total_results);
                                sk.add(s);
                            }
                            r = new Report(token,name,last_level,max_level,avg_time_session,total_time,success_ratio,sk);
                            reportList.add(r);

                        }

                        callback.onSuccess(reportList);
                    }
                    else{
                        Log.d("code = 400", "Error child report. List not obtained.");
                        Exception e = new Exception("code = 400, error connecting to server.");
                        throw e;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }

        }){
            @Override
            public Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("params","{\"app_token\":\""+app_token+"\",\"account_id\":\""+account_id+"\",\"child_id\":\""+child_id+"\"}");
                params.put("format", "json");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        return reportList;
    }


}


