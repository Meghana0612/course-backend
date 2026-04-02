package com.coursemanagement.service;

import com.coursemanagement.dto.CourseDTO;
import com.coursemanagement.entity.Course;
import com.coursemanagement.exception.ResourceNotFoundException;
import com.coursemanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        log.info("Fetching course with id: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return mapToDTO(course);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        log.info("Creating new course: {}", courseDTO.getTitle());
        Course course = mapToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return mapToDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        log.info("Updating course with id: {}", id);
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        existingCourse.setTitle(courseDTO.getTitle());
        existingCourse.setDescription(courseDTO.getDescription());
        existingCourse.setInstructor(courseDTO.getInstructor());
        existingCourse.setDurationHours(courseDTO.getDurationHours());
        existingCourse.setPrice(courseDTO.getPrice());
        existingCourse.setCategory(courseDTO.getCategory());
        existingCourse.setLevel(courseDTO.getLevel());
        existingCourse.setIsActive(courseDTO.getIsActive());

        Course updatedCourse = courseRepository.save(existingCourse);
        return mapToDTO(updatedCourse);
    }

    public void deleteCourse(Long id) {
        log.info("Deleting course with id: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> searchCourses(String keyword) {
        log.info("Searching courses with keyword: {}", keyword);
        return courseRepository.searchByKeyword(keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getActiveCourses() {
        return courseRepository.findByIsActive(true)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByPriceRange(Double minPrice, Double maxPrice) {
        return courseRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO enrollStudent(Long id) {
        log.info("Enrolling student in course with id: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        course.setEnrolledStudents(course.getEnrolledStudents() + 1);
        return mapToDTO(courseRepository.save(course));
    }

    private CourseDTO mapToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .durationHours(course.getDurationHours())
                .price(course.getPrice())
                .category(course.getCategory())
                .level(course.getLevel())
                .isActive(course.getIsActive())
                .enrolledStudents(course.getEnrolledStudents())
                .createdAt(course.getCreatedAt() != null ? course.getCreatedAt().toString() : null)
                .updatedAt(course.getUpdatedAt() != null ? course.getUpdatedAt().toString() : null)
                .build();
    }

    private Course mapToEntity(CourseDTO dto) {
        return Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .instructor(dto.getInstructor())
                .durationHours(dto.getDurationHours())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .level(dto.getLevel() != null ? dto.getLevel() : "BEGINNER")
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .enrolledStudents(dto.getEnrolledStudents() != null ? dto.getEnrolledStudents() : 0)
                .build();
    }
}
