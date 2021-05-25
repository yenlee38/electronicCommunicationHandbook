package com.example.electroniccommunicationhandbook.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Parent;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.google.gson.Gson;

public class UserLocalStore {

    public static final String SP_NAME="USER_ECHB";
    private SharedPreferences userLocalStore;
    private static String PARENT= "parent";
    private static String TEACHER ="teacher";
    private static String STUDENT ="student";
    private static String ROLE="role";

   // private Account accountDAO;
    public UserLocalStore(Context context){

        userLocalStore= context.getSharedPreferences(SP_NAME,0);
    }
    /*
    Save teacher informaton to local
     */
    public void storeTeacher(Teacher teacher){
        Gson gson=  new Gson();
        String json= gson.toJson(teacher);
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.putString(TEACHER,json);
        editor.commit();
    }

    /*
    Save student to local
     */
    public void storeStudent (Student student){
        Gson gson= new Gson();
        String json = gson.toJson(student);
        SharedPreferences.Editor  editor= userLocalStore.edit();
        editor.putString(STUDENT,json);
        editor.commit();
    }
    /*
    Get teacher infor form Local
     */
    public Teacher getTeacherLocal(){

        Gson gson= new Gson();
        String json= userLocalStore.getString(TEACHER,null);
        Teacher teacher= new Teacher();
        teacher= gson.fromJson(json, Teacher.class);
        return teacher;
    }
    /*
    Get student infor from local
     */
    public Student getStudentLocal(){
        Gson gson= new Gson();
        String json= userLocalStore.getString(STUDENT,null);
        Student student= new Student();
        student= gson.fromJson(json, Student.class);
        return student;
    }

    /*
    get parent from local
     */
    public Parent getParentLocal(){
        Gson gson= new Gson();
        String json= userLocalStore.getString(PARENT,null);
        Parent parent= new Parent();
        parent= gson.fromJson(json,Parent.class);
        return parent;
    }
    /*
    Save parent to local
     */
    public void storeParent(Parent parent){
        Gson gson= new Gson();
        String json = gson.toJson(parent);
        SharedPreferences.Editor  editor= userLocalStore.edit();
        editor.putString(PARENT,json);
        editor.commit();
    }

    /*
    Set role =1 : Teacher
        role= 2 : Student
        role=3 : parent
     */
    public void setRoleLocal(int role){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.putInt(ROLE,role);
    }

    public int getRoleLocal(){
        return userLocalStore.getInt(ROLE,0);
    }

    /*
     Clear all user and role when logout
     */
    public void resetUserLocal (){
        clearParent();
        clearStudent();
        clearRole();
        clearTeacher();
    }

    public void clearParent(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.remove(PARENT).apply();
    }

    public void clearStudent(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.remove(STUDENT).apply();
    }

    public void clearTeacher(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.remove(TEACHER).apply();
    }

    public void clearRole(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.remove(ROLE).apply();
    }

    public void clearUser(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.clear();
        editor.commit();
    }
    /*
    public void setUserLogined(boolean bool){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.putBoolean("logined",bool);
        editor.commit();
    }

    public void setRememberPass(boolean rememberme){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.putBoolean("rememberpass",rememberme);
        editor.commit();
    }

    public boolean checkRememberPass(){
        if(userLocalStore.getBoolean("rememberpass",false))
            return true;
        return false;
    }

    public boolean checkUserLogin(){
        if(userLocalStore.getBoolean("logined",false))
            return true;
        return false;
    }

    public void clearUser(){
        SharedPreferences.Editor editor= userLocalStore.edit();
        editor.clear();
        editor.commit();
    }*/
}
