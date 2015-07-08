package wa.filipe.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wa.filipe.finalproject.module.Build;
import wa.filipe.finalproject.module.BuildDAO;

public class Edit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent it = getIntent();

        if(it.hasExtra("posicao")){
            Build build = (Build) it.getSerializableExtra("build");
            int posicao = it.getIntExtra("posicao", 0);

            EditText mod1 = (EditText)findViewById(R.id.editText);
            EditText mod2 = (EditText)findViewById(R.id.editText2);
            EditText mod3 = (EditText)findViewById(R.id.editText3);
            EditText mod4 = (EditText)findViewById(R.id.editText4);
            EditText mod5 = (EditText)findViewById(R.id.editText5);
            EditText mod6 = (EditText)findViewById(R.id.editText6);
            EditText mod7 = (EditText)findViewById(R.id.editText7);
            EditText mod8 = (EditText)findViewById(R.id.editText8);

            String[] mods_stored = build.getMods().split("\\s+");

            mod1.setText(mods_stored[0]);
            mod2.setText(mods_stored[1]);
            mod3.setText(mods_stored[2]);
            mod4.setText(mods_stored[3]);
            mod5.setText(mods_stored[4]);
            mod6.setText(mods_stored[5]);
            mod7.setText(mods_stored[6]);
            mod8.setText(mods_stored[7]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

    public void onClickSave(View v){
        int update_rank, cat;
        int update_slots=0;
        TextView slots = (TextView)findViewById(R.id.textSlot);
        TextView rank = (TextView)findViewById(R.id.textRank);
        EditText mod1 = (EditText)findViewById(R.id.editText);
        EditText mod2 = (EditText)findViewById(R.id.editText2);
        EditText mod3 = (EditText)findViewById(R.id.editText3);
        EditText mod4 = (EditText)findViewById(R.id.editText4);
        EditText mod5 = (EditText)findViewById(R.id.editText5);
        EditText mod6 = (EditText)findViewById(R.id.editText6);
        EditText mod7 = (EditText)findViewById(R.id.editText7);
        EditText mod8 = (EditText)findViewById(R.id.editText8);
        CheckBox catalyst = (CheckBox)findViewById(R.id.checkBox);

        String mods =  mod1.getText().toString() + " " +
                mod2.getText().toString() + " " +
                mod3.getText().toString() + " " +
                mod4.getText().toString() + " " +
                mod5.getText().toString() + " " +
                mod6.getText().toString() + " " +
                mod7.getText().toString() + " " +
                mod8.getText().toString();

        if(Integer.parseInt(mod1.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod2.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod3.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod4.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod5.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod6.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod7.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod8.getText().toString()) > 0) update_slots++;

        int total_slots = Integer.parseInt(mod1.getText().toString()) +
                Integer.parseInt(mod2.getText().toString()) +
                Integer.parseInt(mod3.getText().toString()) +
                Integer.parseInt(mod4.getText().toString()) +
                Integer.parseInt(mod5.getText().toString()) +
                Integer.parseInt(mod6.getText().toString()) +
                Integer.parseInt(mod7.getText().toString()) +
                Integer.parseInt(mod8.getText().toString());

        if(catalyst.isChecked()){
            cat = 1;
            update_rank = (total_slots + 1) / 2;
        } else {
            cat = 0;
            update_rank = total_slots;
        }
        rank.setText(String.valueOf(update_rank));
        slots.setText(String.valueOf(update_slots)+"/8");


        if(total_slots > 30 && !catalyst.isChecked()){
            Toast.makeText(this, "Obrigatório uso de Orokin Catalyst na build!", Toast.LENGTH_SHORT).show();
            catalyst.setChecked(true);
        }

        if(update_slots < 8){
            slots.setTextColor(Color.YELLOW);
        }

        if(update_slots == 8){
            slots.setTextColor(Color.CYAN);
        }

        if(update_rank > 60){
            rank.setTextColor(Color.RED);
        } else {
            rank.setTextColor(Color.GREEN);
        }

        Build build = new Build(update_slots, update_rank, cat, mods);
        BuildDAO dao = new BuildDAO(this);
        if(dao.Insert(build)) {
            Toast.makeText(this, "Salvando...", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Report.class));
        } else {
            Toast.makeText(this, "Problema ao salvar!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCalc(View v){
        int update_rank;
        int update_slots=0;
        TextView slots = (TextView)findViewById(R.id.textSlot);
        TextView rank = (TextView)findViewById(R.id.textRank);
        EditText mod1 = (EditText)findViewById(R.id.editText);
        EditText mod2 = (EditText)findViewById(R.id.editText2);
        EditText mod3 = (EditText)findViewById(R.id.editText3);
        EditText mod4 = (EditText)findViewById(R.id.editText4);
        EditText mod5 = (EditText)findViewById(R.id.editText5);
        EditText mod6 = (EditText)findViewById(R.id.editText6);
        EditText mod7 = (EditText)findViewById(R.id.editText7);
        EditText mod8 = (EditText)findViewById(R.id.editText8);
        CheckBox catalyst = (CheckBox)findViewById(R.id.checkBox);

        if(Integer.parseInt(mod1.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod2.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod3.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod4.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod5.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod6.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod7.getText().toString()) > 0) update_slots++;
        if(Integer.parseInt(mod8.getText().toString()) > 0) update_slots++;

        int total_slots = Integer.parseInt(mod1.getText().toString()) +
                Integer.parseInt(mod2.getText().toString()) +
                Integer.parseInt(mod3.getText().toString()) +
                Integer.parseInt(mod4.getText().toString()) +
                Integer.parseInt(mod5.getText().toString()) +
                Integer.parseInt(mod6.getText().toString()) +
                Integer.parseInt(mod7.getText().toString()) +
                Integer.parseInt(mod8.getText().toString());

        if(catalyst.isChecked()){
            update_rank = (total_slots + 1) / 2;
        } else {
            update_rank = total_slots;
        }
        rank.setText(String.valueOf(update_rank));
        slots.setText(String.valueOf(update_slots)+"/8");


        if(total_slots > 30 && !catalyst.isChecked()){
            Toast.makeText(this, "Obrigatório uso de Orokin Catalyst na build!", Toast.LENGTH_SHORT).show();
            catalyst.setChecked(true);
        }

        if(update_slots < 8){
            slots.setTextColor(Color.YELLOW);
        }

        if(update_slots == 8){
            slots.setTextColor(Color.CYAN);
        }

        if(update_rank > 60){
            rank.setTextColor(Color.RED);
        } else {
            rank.setTextColor(Color.GREEN);
        }

    }

}
