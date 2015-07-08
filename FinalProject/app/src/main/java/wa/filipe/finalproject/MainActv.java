package wa.filipe.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import wa.filipe.finalproject.module.User;
import wa.filipe.finalproject.module.UserDAO;

public class MainActv extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_actv, menu);
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

    public void onClickLogin(View v){
        EditText editUsername = (EditText)findViewById(R.id.editUsername);
        EditText editPassword = (EditText)findViewById(R.id.editPassword);
        UserDAO dao = new UserDAO(this);
        if(dao.getByUsernamePassword(editUsername.getText().toString(), editPassword.getText().toString()) != null) {
            Toast.makeText(this, "Efetuando login...", Toast.LENGTH_SHORT).show();
            startActivity(
                    new Intent(this, Edit.class)
            );
        } else {
            Toast.makeText(this, "Usuário/Senha inválido(s)!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCreate(View v){
        startActivity(new Intent(this, Config.class));
    }

    public void onClickReport(View v){
        startActivity(new Intent(this, Report.class));
    }

    public void onClickAbout(View v){
        startActivity(new Intent(this, About.class));
    }

}
