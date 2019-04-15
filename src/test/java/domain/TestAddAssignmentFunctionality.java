package domain;

import domain.Student;
import domain.Tema;
import org.junit.Test;
import org.junit.Before;
import repository.*;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;


public class TestAddAssignmentFunctionality {
    Service service;
    TemaXMLRepository fileRepository2 ;
    TemaRepository temaRepository;

    @Before
    public void setup() {
        temaRepository = new TemaRepository(new TemaValidator());
      //  StudentXMLRepository fileRepository1 = new StudentXMLRepository(new StudentValidator(), "studenti.xml");
      //  TemaXMLRepository fileRepository2 = new TemaXMLRepository(new TemaValidator(), "teme.xml");
      //  NotaXMLRepository fileRepository3 = new NotaXMLRepository(new NotaValidator(), "note.xml");
      //  service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void shouldAddValidAssignment() {
        Tema temaToAdd = new Tema("5","lorena",4,1);
       // assert service.saveTema("5","lorena",4,1) == 0;

       // Tema result = fileRepository2.save(temaToAdd);
        Tema result = temaRepository.save(temaToAdd);
        if (result == null) {
            assertTrue(true);
        } else
            fail();
    }

    @Test
    public void shouldNotAddInvalidAssignment() {
        Tema temaToAdd = new Tema("6","lorena",10,11);
       // assert service.saveTema("6","lorena",10,11) == 1 ;
        //assert fileRepository2.findOne("3").equals(temaToAdd) ;
            Tema result = temaRepository.save(temaToAdd);
           // Tema result = fileRepository2.save(temaToAdd);

            if (result == null) {
                assertTrue(true);
            } else
                fail();
    }

    @Test
    public void shouldNotAddIfAlreadyPresent(){
        Tema temaToAdd = new Tema("5","lorena",4,1);
        Tema result = temaRepository.save(temaToAdd);

        Tema temaToAdd2 = new Tema("5","lorena",4,1);
        Tema result2 = temaRepository.save(temaToAdd2);

        if (result2 != null) {
            assertTrue(true);
        } else
            fail();

    }

    @Test
    public void shouldNotAddInvalidDescription() {
        Tema temaToAdd = new Tema("6","",10,11);
        Tema result = temaRepository.save(temaToAdd);

        if (result == null) {
            assertTrue(true);
        } else
            fail();
    }


}




