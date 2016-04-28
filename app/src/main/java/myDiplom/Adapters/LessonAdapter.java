package myDiplom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import myDiplom.model.Lesson;
import schedule.mobile.android.mydiplom.ru.mobileschedule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 28.04.2016.
 */
public class LessonAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<Lesson> objects;

    public LessonAdapter(Context context, List<Lesson> objects) {
        this.context = context;
        this.objects = objects;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view==null){
            view = layoutInflater.inflate(R.layout.my_list_lesson_view, parent, false);
        }

        Lesson lesson = getLesson(position);

        //заполняю виьюшку
        ((TextView)view.findViewById(R.id.textTime)).setText(lesson.getLessonTime());
        ((TextView)view.findViewById(R.id.textLessonName)).setText(lesson.getLessonName());
        ((TextView)view.findViewById(R.id.textTeacher)).setText(lesson.getTeachersName());
        ((TextView)view.findViewById(R.id.textKabinet)).setText(lesson.getKabinet());


        return view;
    }

    private Lesson getLesson(int position) {
        return (Lesson) getItem(position);
    }


}
