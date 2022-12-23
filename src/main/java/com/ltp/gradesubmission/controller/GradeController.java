package com.ltp.gradesubmission.controller;

import com.ltp.gradesubmission.model.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    List<Grade> studentsGrades = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model){
        model.addAttribute("grade", new Grade());
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade){
        studentsGrades.add(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentsGrades);
        return "grades";
    }
}
