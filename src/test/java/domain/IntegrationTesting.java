package domain;

import domain.Student;
import org.junit.Test;
import org.junit.Before;
import repository.*;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;


public class IntegrationTesting {

    TemaRepository temaRepository;
    StudentRepository studentRepository;
    NotaRepository notaRepository;

    @Before
    public void setup() {
        temaRepository = new TemaRepository(new TemaValidator());
        studentRepository = new StudentRepository(new StudentValidator());
        notaRepository = new NotaRepository(new NotaValidator());
    }

    @Test
    public void addStudent() {
        Student student = new Student("10","Lorena", 936);
        studentRepository.save(student);
        assert studentRepository.findOne("10").equals(student);
    }

    @Test
    public void addAssignment() {
        Tema temaToAdd = new Tema("5","lorena",4,1);
        temaRepository.save(temaToAdd);
        assert temaRepository.findOne("5").equals(temaToAdd);
    }

    @Test
    public void addGrade(){
        Nota gradeToAdd = new Nota(new Pair<>("10","5"),10,6,"foarte bine");
        notaRepository.save(gradeToAdd);
        Pair pairToSearch = new Pair("10","5");
        assert notaRepository.findOne(pairToSearch).equals(gradeToAdd);
    }

    @Test
    public void addAll() {
        addStudent();
        addAssignment();
        addGrade();
    }

    @Test
    public void addAssignmentTopDown() {
        Student student = new Student("10","Lorena", 936);
        studentRepository.save(student);


        Tema temaToAdd = new Tema("5","lorena",4,1);
        temaRepository.save(temaToAdd);

        assert temaRepository.findOne("5").equals(temaToAdd) &&
                studentRepository.findOne("10").equals(student);;
    }

    @Test
    public void addGradeTopDown() {
        Student student = new Student("10","Lorena", 936);
        studentRepository.save(student);


        Tema temaToAdd = new Tema("5","lorena",4,1);
        temaRepository.save(temaToAdd);

        Nota gradeToAdd = new Nota(new Pair<>("10","5"),10,6,"foarte bine");
        notaRepository.save(gradeToAdd);
        Pair pairToSearch = new Pair("10","5");


        assert temaRepository.findOne("5").equals(temaToAdd) &&
                studentRepository.findOne("10").equals(student) &&
                notaRepository.findOne(pairToSearch).equals(gradeToAdd);
    }

}




