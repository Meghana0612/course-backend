package com.coursemanagement.controller;

import com.coursemanagement.dto.CourseDTO;
import com.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    // GET /api/courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // GET /api/courses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // POST /api/courses
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO created = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    // DELETE /api/courses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(Map.of(
                "message", "Course deleted successfully",
                "id", id.toString()
        ));
    }

    // GET /api/courses/search?keyword=java
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    // GET /api/courses/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseDTO>> getCoursesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(category));
    }

    // GET /api/courses/active
    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> getActiveCourses() {
        return ResponseEntity.ok(courseService.getActiveCourses());
    }

    // GET /api/courses/price-range?minPrice=0&maxPrice=100
    @GetMapping("/price-range")
    public ResponseEntity<List<CourseDTO>> getCoursesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(courseService.getCoursesByPriceRange(minPrice, maxPrice));
    }

    // POST /api/courses/{id}/enroll
    @PostMapping("/{id}/enroll")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.enrollStudent(id));
    }
}
