package com.example.apiresful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ingress(View view)
    {

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }


    @Override
    public void processFinish(String result) throws JSONException
    {
        TextView inform = (TextView) findViewById(R.id.txthola);
        inform.setText("Resp: " + inform );

        JSONArray jsonArray = new JSONArray(result);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String age = jsonObject.getString("phone");

            stringBuilder.append("Name: ").append(name).append("\n\n");
            stringBuilder.append("Email: ").append(email).append("\n\n");
            stringBuilder.append("Phone: ").append(age).append("\n\n");
        }

        inform.setText(stringBuilder.toString());
    }
}



