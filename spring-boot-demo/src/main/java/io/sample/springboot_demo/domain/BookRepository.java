package io.sample.springboot_demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByAuthor(String author);
    
    List<Book> findByAuthorAndStatus(String author, int status);
    
    List<Book> findByDescriptionEndsWith(String desEnd);
    
    @Query("SELECT b from Book b where length(b.name) > ?1 ")
    List<Book> findByJPQL(int len);
    
    @Query(value = "SELECT * FROM book WHERE LENGTH(name) > ?1", nativeQuery = true)
    List<Book> findBySQL(int len);
}
