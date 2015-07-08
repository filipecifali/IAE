package wa.filipe.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import wa.filipe.finalproject.module.User;
import wa.filipe.finalproject.module.UserDAO;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Config extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ListView lv = (ListView) findViewById(R.id.lvWarframe);
        String[] warframes = new String[] {"Ember", "Rhino", "Volt", "Excalibur", "Frost", "Trinity"};
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < warframes.length; ++i){ list.add(warframes[i]); }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onClickConfig(View v){
        EditText editUsername = (EditText)findViewById(R.id.editUsername);
        EditText editPassword = (EditText)findViewById(R.id.editPassword);
        ListView lv = (ListView)findViewById(R.id.lvWarframe);
        String frame = (String) lv.getSelectedItem();
        User user = new User(editUsername.getText().toString(), editPassword.getText().toString(), frame);
        UserDAO dao = new UserDAO(this);
        if(dao.Insert(user)){
            Toast.makeText(this, "Alterações salvas!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActv.class));
        } else {
            Toast.makeText(this, "Problema ao salvar!!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}
