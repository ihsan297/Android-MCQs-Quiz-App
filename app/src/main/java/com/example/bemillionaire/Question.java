package com.example.bemillionaire;
// Class for a single Question
public class Question {
    int id; //Q id
    String question;// Question text
    //Options
    String option_a;
    String option_b;
    String option_c;
    String option_d;
    String ans; //Answer key of question
//Constructor
    public Question(String question, String option_a, String option_b, String option_c, String option_d, String ans) {
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.ans = ans;
    }

    public Question() {
        this.id=0;
        this.question ="";
        this.option_a = "";
        this.option_b = "";
        this.option_c = "";
        this.option_d = "";
        this.ans = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption_a() {
        return option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public String getAns() {
        return ans;
    }
}
