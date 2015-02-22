package sabadell.grupo18.enei;

import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {

    private RecyclerView lstOferta;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bar= (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(100);
        changeProgressBar(bar,25);



        lstOferta= (RecyclerView) findViewById(R.id.lstOferta);

        manager=new LinearLayoutManager(this);
        lstOferta.setLayoutManager(manager);

        adapter=new AdapterOferta(this);
        lstOferta.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void changeProgressBar(ProgressBar bar, int value){
        bar.setProgress(25);
        if(bar.getProgress()/bar.getMax()<40){
            bar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


        }else if(bar.getProgress()/bar.getMax()>40 && bar.getProgress()/bar.getMax()<60){
            bar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else{
            bar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        }

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
