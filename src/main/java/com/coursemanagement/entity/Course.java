package com.coursemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    @NotBlank(message = "Instructor is required")
    @Column(nullable = false)
    private String instructor;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Column(nullable = false)
    private Integer durationHours;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String level; // BEGINNER, INTERMEDIATE, ADVANCED

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(name = "enrolled_students")
    private Integer enrolledStudents = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
