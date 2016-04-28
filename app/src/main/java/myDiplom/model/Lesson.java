package myDiplom.model;

/**
 * I't our model class of lessons
 *
 */
public class Lesson {
    private String lessonTime;
    private String lessonName;
    private String teachersName;
    private String kabinet;

    public Lesson() {
    }

    public Lesson(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(String teachersName) {
        this.teachersName = teachersName;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lesson{");
        sb.append("lessonTime='").append(lessonTime).append('\'');
        sb.append(", lessonName='").append(lessonName).append('\'');
        sb.append(", teachersName='").append(teachersName).append('\'');
        sb.append(", kabinet='").append(kabinet).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
