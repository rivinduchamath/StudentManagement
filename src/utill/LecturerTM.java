package utill;

public class LecturerTM {
    private String subjectId;
    private String lecturerName;
    private String subject;


    public LecturerTM(String subjectId, String lecturerName, String subject) {

        this.subjectId = subjectId;
        this.lecturerName = lecturerName;
        this.subject = subject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "LecturerTM{" +
                "subjectId='" + subjectId + '\'' +
                ", lecturerName='" + lecturerName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}