package com.testsystem.TestConstructor.models;

import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;

    private boolean flag;

    @ManyToOne
    private Question question;

    public Answer(){}
    public Answer(Question question,String answer, boolean flag) {
        this.question=question;
        this.answer = answer;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Question getQuestion() {
        return question;
    }//????

    public void setQuestion(Question question) {
        this.question = question;
    }//????
}
