package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import myDiplom.excel.read.FAO.ReadSheduler;

import java.util.*;

public class Activity_third_group_choose extends AppCompatActivity {
    private String facultet;
    private static Map.Entry<String, Integer> choosenGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_group_choose);

        facultet = Activity_second_facult_choose.getChosenFac();
        setTitle("Выбор группы");

        final List<Map<String, Integer>> listOfMapGroupsColId = ReadSheduler.getGroupsByFacultet(facultet);
        final List<String> listOfGroups = new ArrayList<>();
        for (Map<String, Integer> map : listOfMapGroupsColId) {
            for (String s : map.keySet()) {
                listOfGroups.add(s);
            }
        }
        Log.d("LOG_LOG: list of groups","array: " + listOfGroups);

        //set listView with groups
        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listOfGroups.toArray(new String[listOfGroups.size()]));

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chosenGroup = listOfGroups.get(position);
                Log.d("\nLOG_LOG: ", "group chosen: " + chosenGroup);

                for (Map<String, Integer> map : listOfMapGroupsColId) {
                    if (map.containsKey(chosenGroup)){
                        setChoosenGroup(Collections.singletonMap(chosenGroup, map.get(chosenGroup)));
                    }
                }
                Intent intent = new Intent(adapter.getContext(), Activity_forth_day_choose.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_third_group_choose, menu);
        return true;
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

    public static Map.Entry<String, Integer> getChoosenGroup() {
        return choosenGroup;
    }

    public static void setChoosenGroup(Map<String, Integer> choosenGroup) {
        for (Map.Entry<String, Integer> entry : choosenGroup.entrySet()) {

            Activity_third_group_choose.choosenGroup = entry
            ;
        }
    }
}
