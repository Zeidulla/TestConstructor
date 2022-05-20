package com.testsystem.TestConstructor.models;

import javax.persistence.*; /* сделаю пометку */

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long trueAns; /* сделаю пометку */

    @ManyToOne
    private User user;

    @ManyToOne
    private Test test;

    public Result() {}

    public Result(Long trueAns, User user, Test test) {/* сделаю пометку */
        this.trueAns = trueAns;
        this.user = user;
        this.test = test;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTrueAns() {
        return trueAns;
    }
    public void setTrueAns(Long trueAns) {/* сделаю пометку */
        this.trueAns = trueAns;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Test getTest() {
        return test;
    }
    public void setTest(Test test){
        this.test = test;
    }

}
