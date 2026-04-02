package com.coursemanagement.repository;

import com.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCategory(String category);

    List<Course> findByInstructor(String instructor);

    List<Course> findByIsActive(Boolean isActive);

    List<Course> findByLevel(String level);

    List<Course> findByPriceBetween(Double minPrice, Double maxPrice);

    @Query("SELECT c FROM Course c WHERE " +
           "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> searchByKeyword(@Param("keyword") String keyword);

    List<Course> findByCategoryAndLevel(String category, String level);

    long countByIsActive(Boolean isActive);
}
