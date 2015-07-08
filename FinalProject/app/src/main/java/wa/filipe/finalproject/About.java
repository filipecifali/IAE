package wa.filipe.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void finalizar(View v)
    {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
