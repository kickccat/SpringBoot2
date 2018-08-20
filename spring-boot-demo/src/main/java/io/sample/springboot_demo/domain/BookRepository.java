package io.sample.springboot_demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByAuthor(String author);
    
    List<Book> findByAuthorAndStatus(String author, int status);
    
    List<Book> findByDescriptionEndsWith(String desEnd);
    
    @Query("SELECT b from Book b where length(b.name) > ?1 ")
    List<Book> findByJPQL(int len);
    
    @Query(value = "SELECT * FROM book WHERE LENGTH(name) > ?1", nativeQuery = true)
    List<Book> findBySQL(int len);
    
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.status = ?1 WHERE b.id = ?2")
    int updateByJPQL(int status, long id);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = ?1")
    int deleteByJPQL(long id);
}
