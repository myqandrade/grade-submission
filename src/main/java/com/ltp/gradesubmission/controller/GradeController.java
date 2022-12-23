package com.ltp.gradesubmission.controller;

import com.ltp.gradesubmission.model.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    List<Grade> studentsGrades = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String name){
        model.addAttribute("grade", getGradeIndex(name) == -1000 ? new Grade() : studentsGrades.get(getGradeIndex(name)));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade){
        int index = getGradeIndex(grade.getName());
        if(index == -1000){
            studentsGrades.add(grade);
        } else {
            studentsGrades.set(index, grade);
        }
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentsGrades);
        return "grades";
    }

    public Integer getGradeIndex(String name){
        for (int i = 0; i < studentsGrades.size(); i++) {
            if(studentsGrades.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1000;
    }
}
