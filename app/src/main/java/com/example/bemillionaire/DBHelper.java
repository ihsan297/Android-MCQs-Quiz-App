package com.example.bemillionaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
//Class for Data operations and Create and add quesions
public class DBHelper extends SQLiteOpenHelper {

    Context context;
    static final  String DB_NAME="qs.db";
    private static final int DB_VERSION = 3;
    private static final String CREATE_TABLE = "CREATE TABLE quiz (id INTEGER PRIMARY KEY AUTOINCREMENT , question VARCHAR(255), option_a VARCHAR(255), option_b  VARCHAR(255), option_c VARCHAR(255), option_d  VARCHAR(255), ans VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS quiz";
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context=context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
    void  createQuestions(){//Create all questions
        ArrayList<Question> questions = new ArrayList<>();//list of Questions
        questions.add(new Question("Which country won 2019 Cricket WorldCup","Australia","India","Pakistan","England","England"));
        questions.add(new Question("Which country is organizing 2022 FIFA World Cup","Spain","Brazil","Qatar","China","Qatar"));
        questions.add(new Question("Owner of Twitter","Mark Zuck","Steve Jobs","Jack Dorsey","None","Jack Dorsey"));
        questions.add(new Question("Who was the founder of company Google ?", "Steve Jobs", "Bill Gates", "Larry Page", "Sundar Pichai", "Larry Page"));
        questions.add(new Question("How many states does Malaysia have?","12","13","14","14","13"));
        questions.add(new Question("The world’s first Photograph was taken in __________ ?","1927","1825","1826","1926","1826"));
        addToDB(questions);
    }
    void addToDB(ArrayList<Question> questions){//add all questions to SQLite DB
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues contentValues=new ContentValues();
        for(Question question: questions){
            contentValues.put("question",question.question);
            contentValues.put("option_a",question.option_a);
            contentValues.put("option_b",question.option_b);
            contentValues.put("option_c",question.option_c);
            contentValues.put("option_d",question.option_d);
            contentValues.put("ans",question.ans);
            sqLiteDatabase.insert("quiz",null,contentValues);//inseritmg current row to DB

        }
        Log.d("Size",Integer.toString(questions.size()));


        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();
    }
    List<Question> getData(){// get all questions from DB
        List<Question> questions=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        sqLiteDatabase.beginTransaction();
        String cols[]={"question","option_a","option_b","option_c","option_d","ans"};//column names
        Cursor cursor=sqLiteDatabase.query("quiz",cols,null,null,null,null,null);
        while(cursor.moveToNext()){
            //Getting all questions at index

            Question question=new Question();
            question.setQuestion(cursor.getString(0));
            Log.d("Got",question.question);
            question.setOption_a(cursor.getString(1));
rfdc            question.setOption_b(cursor.getString(2));
            question.setOption_c(cursor.getString(3));
            question.setOption_d(cursor.getString(4));
            question.setAns(cursor.getString(5));
            questions.add(question);
        }
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        cursor.close();
        return questions;
    }


}
