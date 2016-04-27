package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.TextView;
import org.apache.poi.ss.util.CellAddress;

import java.util.Map;

public class Activity_fifth_shedule_for_group extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_shedule_for_group);

        setTitle("Расписание");

        String chosenDay = Activity_forth_day_choose.getChosenDay();
        CellAddress cellAddressOfDay = Activity_forth_day_choose.getCellAddressOfDay();
        Map.Entry<String, Integer> chosenGroupAndColumn = Activity_forth_day_choose.getChosenGroup();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(chosenDay + "\n" + cellAddressOfDay + "\n"
                + chosenGroupAndColumn.getKey() + "\n" + chosenGroupAndColumn.getValue());

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
