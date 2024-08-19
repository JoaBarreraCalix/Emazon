// domain.model.Category
package com.example.emazon.domain.model;

public class Category {

    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        validateName(name);
        validateDescription(description);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateName(String name) {
        if (name == null || name.length() > 50) {
            throw new IllegalArgumentException("The name must not exceed 50 characters.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > 90) {
            throw new IllegalArgumentException("The description must not exceed 90 characters.");
        }
    }
}
