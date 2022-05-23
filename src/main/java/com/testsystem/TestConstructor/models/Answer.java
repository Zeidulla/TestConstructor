package com.testsystem.TestConstructor.models;

import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    private  boolean flag;/* сделаю пометку */
    @ManyToOne
    private TypePatternQuestions.Question question; /* нужно проверить! */

    public Answer() {}
    public Answer(TypePatternQuestions.Question question, String answer, boolean flag) {/* сделаю пометку */
        this.question = question;
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
    public TypePatternQuestions.Question getQuestion() {/* нужно проверить! */
        return question;
    }/* нужно проверить! */
    public void setQuestion(TypePatternQuestions.Question question) {/* нужно проверить! */
        this.question = question;
    }/* нужно проверить! */
}
