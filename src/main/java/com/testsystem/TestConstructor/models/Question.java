package com.testsystem.TestConstructor.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answer;

    @ManyToOne
    private Test test;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {/* сделаю пометку */
        this.question = question;
    }
    public List<Answer> getAnswer() {
        return answer;
    }
    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
    public Test getTest() {
        return test;/* сделаю пометку */
    }
    public void setTest(Test test) {
        this.test = test;/* сделаю пометку */
    }
}
