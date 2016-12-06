package com.shinitai.nicolle.asynctask1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private String[] names = {"絶園のテンペスト", "ラブひな", "東京グール", "未来日記", "僕だけがいない街", "ひぐらしのなく頃に", "僕らがいた",
            "黒執事", "僕と犬SS", "神のみぞ知る世界", "クラナド", "ニセコイ", "迷家", "終わりのセラフ", "君に届け", "君が望む永遠", "はたらく魔王さま", "極黒の"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void,String,String>
    {
        ArrayAdapter<String> adapter;
        ProgressBar progressBar;
        int count;
        @Override
        protected void onPreExecute()
        {
            adapter = (ArrayAdapter<String>)listView.getAdapter();
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setMax(18);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
            count = 0;
        }

        @Override
        protected String doInBackground(Void... params)
        {
            for (String Name: names)
            {
                publishProgress(Name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "いいアニメだね！";
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count);
        }

        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    }
}
