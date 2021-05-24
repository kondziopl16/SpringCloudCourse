package com.kondziopl16.students.controller;

import com.kondziopl16.students.repository.StudentRepository;
import com.kondziopl16.students.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudents(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Student> getStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());

    }
}
