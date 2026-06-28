package com.GymTrackerBackend.model;

import java.io.Serializable;
import java.util.Objects;

public class LibraryTemplateId implements Serializable {

    private User user;
    private Template template;

    public LibraryTemplateId() {}

    public LibraryTemplateId(User user, Template template) {
        this.user = user;
        this.template = template;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, template);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryTemplateId other = (LibraryTemplateId) obj;
        return Objects.equals(user, other.user) &&
               Objects.equals(template, other.template);
    }
}
