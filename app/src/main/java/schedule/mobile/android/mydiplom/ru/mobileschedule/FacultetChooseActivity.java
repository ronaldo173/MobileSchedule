package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import myDiplom.myDialogs.OpenFileDialog;

import java.io.File;


public class FacultetChooseActivity extends AppCompatActivity {
    private static String filename = null;
    private File fileExcel = new File("Shududer_1kurs.xls");
    private File fileForRead = null;

    public static String getFileName() {
        return filename;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultet_choose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        ListView listView = (ListView) findViewById(R.id.listView);
//        String[] arrayFacultets = getArrayFacultets();
//
//        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                arrayFacultets);
//
//        listView.setAdapter(adapter);


//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText("selected file: " + fileForRead.getName() + "\n" +
//        fileForRead.length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facultet_choose, menu);
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

    public void onOpenFileClick(View view) {
        final OpenFileDialog fileDialog = new OpenFileDialog(this);


        fileDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filename = fileDialog.getResultFile().getAbsolutePath();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        AlertDialog dialog = fileDialog.show();
        final Intent intent = new Intent(this, SecondActivity.class);
        //go to 2 activity

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);

                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();



    }

}
