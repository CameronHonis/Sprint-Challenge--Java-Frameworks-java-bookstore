package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void b_findBookById()
    {
        assertEquals("Flatterland", bookService.findBookById(26).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void c_notFindBookById()
    {
        assertEquals("", bookService.findBookById(10000).getTitle());
    }

    @Test
    public void z_delete()
    {
        int currSize = bookService.findAll().size();
        bookService.delete(26);
        assertEquals(currSize-1, bookService.findAll().size());
    }

    @Test
    public void e_save()
    {
        Section s1 = new Section("Fiction");
        s1.setSectionid(21);
        Book b1 = new Book("Flatterland", "9780738206752", 2001, s1);
        b1 = bookService.save(b1);
        assertNotNull(b1);
        assertEquals("Flatterland", b1.getTitle());
    }

    @Test
    public void f_update()
    {
        Section s1 = new Section("Fiction");
        s1.setSectionid(21);
        Book b1 = new Book("Flatterlanddd", "9780738206752", 2001, s1);
        bookService.update(b1, 26);
        assertEquals("Flatterlanddd", bookService.findBookById(26).getTitle());
    }

    @Test
    public void zz_deleteAll()
    {
        bookService.deleteAll();
        assertEquals(0, bookService.findAll().size());
    }
}