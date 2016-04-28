package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import myDiplom.Adapters.LessonAdapter;
import myDiplom.excel.read.FAO.ReadSheduler;
import myDiplom.model.Lesson;
import org.apache.poi.ss.util.CellAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity_fifth_shedule_for_group extends AppCompatActivity {
    private String dataSpinner[] = {"Первая неделя", "Вторая неделя"};
    private int selectedWeek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_shedule_for_group);
        String chosenDay = Activity_forth_day_choose.getChosenDay();
        CellAddress cellAddressOfDay = Activity_forth_day_choose.getCellAddressOfDay();
        Map.Entry<String, Integer> chosenGroupAndColumn = Activity_forth_day_choose.getChosenGroup();
        setTitle("Расписание - " + chosenDay);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, R.layout.my_spinner_it, dataSpinner);
        spinner.setAdapter(spinAdapter);
        spinner.setSelection(0);



        Map<Integer, List<Lesson>> sheduleByDay = null;
        try {
            sheduleByDay = ReadSheduler.getSheduleByDay(chosenGroupAndColumn, cellAddressOfDay, chosenDay);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ListView listView = (ListView) findViewById(R.id.listView);
        final List<Lesson> listLessons = new ArrayList<>(sheduleByDay.get(new Integer(selectedWeek)));
        final LessonAdapter lessonAdapter = new LessonAdapter(this, listLessons);
        listView.setAdapter(lessonAdapter);


        final Map<Integer, List<Lesson>> finalSheduleByDay = sheduleByDay;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedWeek = position;

                listLessons.clear();
                listLessons.addAll(new ArrayList<Lesson>(finalSheduleByDay.get(new Integer(position))));
                lessonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_fifth_shedule_for_group, menu);
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
}
