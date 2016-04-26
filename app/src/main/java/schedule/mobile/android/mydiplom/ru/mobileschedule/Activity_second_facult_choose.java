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

import java.io.File;
import java.io.IOException;

public class Activity_second_facult_choose extends AppCompatActivity {
    private static String chosenFac = null;

    public static String getChosenFac() {
        return chosenFac;
    }

    public static void setChosenFac(String chosenFac) {
        Activity_second_facult_choose.chosenFac = chosenFac;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_facultet_choose);
        setTitle("Выбор факультета");


        File fileExcel = StartFileChooseActivity.getFileForRead();
        final ListView listView = (ListView) findViewById(R.id.listView);
        String[] arrayFacultets = null;
        try {
            arrayFacultets = ReadSheduler.readFromExcelFacultets(fileExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                arrayFacultets);

        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final String[] finalArrayFacultets = arrayFacultets;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setChosenFac(finalArrayFacultets[position]);
                Intent intent = new Intent(adapter.getContext(), Activity_third_group_choose.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
