package com.example.electroniccommunicationhandbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.electroniccommunicationhandbook.dao.AccountDAO;
import com.example.electroniccommunicationhandbook.dao.AnnouncementDAO;
import com.example.electroniccommunicationhandbook.dao.ClassDAO;
import com.example.electroniccommunicationhandbook.dao.ConfirmationPaperDAO;
import com.example.electroniccommunicationhandbook.dao.MessageDAO;
import com.example.electroniccommunicationhandbook.dao.ParentDAO;
import com.example.electroniccommunicationhandbook.dao.SchoolTimeDAO;
import com.example.electroniccommunicationhandbook.dao.StudentDAO;
import com.example.electroniccommunicationhandbook.dao.Student_ClassDAO;
import com.example.electroniccommunicationhandbook.dao.Student_ConfirmationPaperDAO;
import com.example.electroniccommunicationhandbook.dao.Student_ParentDAO;
import com.example.electroniccommunicationhandbook.dao.SubjectDAO;
import com.example.electroniccommunicationhandbook.dao.TeacherDAO;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.ConfirmationPaper;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.example.electroniccommunicationhandbook.entity.Student_Parent;
import com.example.electroniccommunicationhandbook.entity.Subject;
import com.example.electroniccommunicationhandbook.entity.Teacher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Account.class, Announcement.class, Class.class, ConfirmationPaper.class, Message.class, Parent.class, SchoolTime.class
, Student.class, Student_Class.class, Student_ConfirmationPaper.class, Student_Parent.class, Subject.class, Teacher.class}, version = 1, exportSchema = false)
@TypeConverters(Convert.class)
public abstract class RoomDB extends RoomDatabase {

    private static String dbName = "ECH";
    private static volatile RoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, dbName).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AccountDAO accountDAO();
    public abstract AnnouncementDAO announcementDAO();
    public abstract ClassDAO classDAO();
    public abstract ConfirmationPaperDAO confirmationPaperDAO();
    public abstract MessageDAO messageDAO();
    public abstract ParentDAO parentDAO();
    public abstract SchoolTimeDAO schoolTimeDAO();
    public abstract Student_ClassDAO student_classDAO();
    public abstract Student_ConfirmationPaperDAO student_confirmationPaperDAO();
    public abstract Student_ParentDAO student_parentDAO();
    public abstract StudentDAO studentDAO();
    public abstract SubjectDAO subjectDAO();
    public abstract TeacherDAO teacherDAO();

}
