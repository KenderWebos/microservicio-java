package com.api.java_api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity // Define as an entity in the database
@Table // Define as a database table
@Builder // Constructor for building objects
@Data // Getter, Setter, toEquals, toHash, toString
@NoArgsConstructor // Adds a no-argument constructor
@AllArgsConstructor // Adds a full constructor
public class Post {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT") // Large text column
    private String content;

    @Column
    private String author;

    @Column
    private String status;

    @CreationTimestamp // Automatically fills the creation timestamp
    @Column(updatable = false) // Prevent update of this field
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatically fills the update timestamp
    @Column
    private LocalDateTime updatedAt;

    @ElementCollection
    private List<String> tags; // List of tags for the post

    // Relationship with User entity (author)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Relationship with Category entity (classify the post)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Relationship with Comment entity (posts can have comments)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column
    private String createdBy;

    @Column
    private String lastModifiedBy;

    // Method to get an excerpt of the content (first 100 characters)
    public String getExcerpt() {
        if (content != null && content.length() > 100) {
            return content.substring(0, 100) + "...";
        }
        return content;
    }

    // Constructor with title and content only (for quick instantiation)
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
