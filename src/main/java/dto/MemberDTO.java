/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Member;

/**
 *
 * @author casper
 */
public class MemberDTO {

    private  String name;
    private  String studentID;
    private  String github;
    
    
    public MemberDTO() {
    }
    public MemberDTO(Member member) {
        
        this.name = member.getName();
        this.studentID = member.getStudentID();
        this.github = member.getGithub();

    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getGithub() {
        return github;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setGithub(String github) {
        this.github = github;
    }
    
    
    
    

}
