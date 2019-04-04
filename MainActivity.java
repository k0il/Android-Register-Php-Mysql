package edu.tutorial.registerphpmysql;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText email,pass,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.txtEmail);
        pass  = (EditText)findViewById(R.id.txtPassword);
        user = (EditText)findViewById(R.id.txtUser);
    }


    public void register(View view)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url =  getResources().getString(R.string.url);

        StringRequest postRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response){
                        Log.d("APPLOG",response);
                        parseJson(response);
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("APPLOG",error.toString());
                    }
                }

        ){
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>();
                params.put("post_email",email.getText().toString().trim());
                params.put("post_passwd",pass.getText().toString().trim());
                params.put("post_username",user.getText().toString().trim());
                return params;
            }

        };
        queue.add(postRequest);
    }


    public void parseJson(String jsonStr)
    {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonStr);
            if(jsonObj.get("REGISTER").equals("OK"))
            {

                showAlert(jsonObj.get("MSG").toString());
            }else{
                showAlert(jsonObj.get("MSG").toString());
            }

        }catch (JSONException e) {

        }
    }

    public void showAlert(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
