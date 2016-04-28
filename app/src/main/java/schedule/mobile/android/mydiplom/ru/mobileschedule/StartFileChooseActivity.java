package schedule.mobile.android.mydiplom.ru.mobileschedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import myDiplom.excel.read.FAO.ReadSheduler;
import myDiplom.myDialogs.OpenFileDialog;

import java.io.File;


public class StartFileChooseActivity extends AppCompatActivity {
    private File fileExcel = new File("Shududer_1kurs.xls");
    private static File fileForRead = null;

    public static File getFileForRead() {
        return fileForRead;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_file_choose_activity);
        setTitle("Мобильное расписание");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        fileDialog.setPositiveButton("Open", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Button buttonGo = (Button) findViewById(R.id.buttonGo);
                fileForRead = fileDialog.getResultFile();
                TextView textView = (TextView) findViewById(R.id.textView);
                String ourChosenFile = null;

                if (fileForRead == null) {
                    ourChosenFile = "Файл не выбран/выбрана папка";
                    buttonGo.setVisibility(View.INVISIBLE);
                } else {
                    ourChosenFile = "Выбран файл: " + fileForRead.toString();
                    buttonGo.setVisibility(View.VISIBLE);
                    ReadSheduler.setFile(fileForRead);
                }

                textView.setText(ourChosenFile);


            }
        });

        AlertDialog alertDialog = fileDialog.show();
    }


    public void onGoToSecondActivity(View view) {
        Intent intent = new Intent(this, Activity_second_facult_choose.class);
        startActivity(intent);
    }
}
