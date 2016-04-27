package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import myDiplom.excel.read.FAO.ReadSheduler;
import org.apache.poi.sl.draw.binding.STTextShapeType;
import org.apache.poi.ss.util.CellAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Activity_forth_day_choose extends AppCompatActivity {
    private static Map.Entry<String, Integer> chosenGroup = null;
    private static CellAddress cellAddressOfDay = null;
    private static String chosenDay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth_day_choose);

        chosenGroup = Activity_third_group_choose.getChoosenGroup();
        setTitle("Группа: " + chosenGroup.getKey());

        LinkedHashMap<String, CellAddress> daysOfWeekAndAdresses = null;
        List<String> listOfDays = null;
        try {
            daysOfWeekAndAdresses = ReadSheduler.getDaysOfWeek();
            listOfDays = new ArrayList<>(daysOfWeekAndAdresses.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }


        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listOfDays.toArray(new String[listOfDays.size()]));

        listView.setAdapter(adapter);

        final List<String> finalListOfDays = listOfDays;
        final LinkedHashMap<String, CellAddress> finalDaysOfWeekAndAdresses = daysOfWeekAndAdresses;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chosenDay = finalListOfDays.get(position);
                CellAddress cellAddressOfDay = finalDaysOfWeekAndAdresses.get(chosenDay);
                setCellAddressOfDay(cellAddressOfDay);
                setChosenDay(chosenDay);

                Intent intent = new Intent(adapter.getContext(), Activity_fifth_shedule_for_group.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forth_day_choose, menu);
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

    public static Map.Entry<String, Integer> getChosenGroup() {
        return chosenGroup;
    }

    public static CellAddress getCellAddressOfDay() {
        return cellAddressOfDay;
    }

    public static String getChosenDay() {
        return chosenDay;
    }

    private static void setCellAddressOfDay(CellAddress cellAddressOfDay) {
        Activity_forth_day_choose.cellAddressOfDay = cellAddressOfDay;
    }

    private static void setChosenDay(String chosenDay) {
        Activity_forth_day_choose.chosenDay = chosenDay;
    }
}
