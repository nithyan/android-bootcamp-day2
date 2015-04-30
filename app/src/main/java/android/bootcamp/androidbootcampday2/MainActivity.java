package android.bootcamp.androidbootcampday2;

import android.app.AlertDialog;
import android.bootcamp.androidbootcampday2.model.Name;
import android.bootcamp.androidbootcampday2.repository.NameRepository;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    NameRepository nameRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameRepository = new NameRepository(this);
        populateItems();
    }

    private void populateItems() {
        ListView listView = (ListView) findViewById(R.id.name_list);

//        String[] names = {"Subha", "Maha", "Nithya", "Gayathri", "Krishna", "Balaji"};

        List<String> names = getNames(nameRepository.getNames());
        String[] namesArray = names.toArray(new String[names.size()]);

        ArrayAdapter<String> nameListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, namesArray);
        listView.setAdapter(nameListAdapter);
    }

    private List<String> getNames(List<Name> names) {
        List<String> nameList = new ArrayList<>();
        for (Name name : names) {
            nameList.add(name.getName());
        }

        return nameList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_item) {
            getInput();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getInput() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name Input");
        builder.setMessage("What's your name?");

        final EditText textInput = new EditText(this);
        textInput.setTextColor(Color.BLACK);

        builder.setView(textInput);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = textInput.getText().toString();
                nameRepository.save(name);
                populateItems();
                Toast.makeText(MainActivity.this, "Your input has been saved!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Your input has been canceled!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
