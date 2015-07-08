package wa.filipe.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import wa.filipe.finalproject.module.BuildDAO;
import wa.filipe.finalproject.module.Build;


public class Report extends ListActivity {
    List<Build> listaBuilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_report);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateLista();
    }

    public void updateLista()
    {
        List<HashMap<String,String>> lista = new ArrayList<HashMap<String, String>>();

        BuildDAO dao = new BuildDAO(this);
        listaBuilds = dao.getAll();

        HashMap<String, String> mapHeader = new HashMap<String, String>();
        mapHeader.put("slots", "SLOTS");
        mapHeader.put("rank", "RANK");
        mapHeader.put("catalyst", "CATALYST");
        lista.add(mapHeader);

        for(Build b: listaBuilds)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("slots", b.getSlots().toString());
            map.put("rank", b.getRank().toString());
            map.put("catalyst", b.getCatalyst().toString());
            lista.add(map);
        }

        String from[] = {"slots","rank","catalyst"};
        int to[] = {R.id.txtSlots, R.id.txtRank,
                R.id.txtCatalyst};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), lista , R.layout.activity_report, from, to);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position > 0){
            Intent it = new Intent(getBaseContext(),Edit.class);
            it.putExtra("build", listaBuilds.get(position-1));
            it.putExtra("posicao", (position-1));
            startActivity(it);

        }
    }
}
