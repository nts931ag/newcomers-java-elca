package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_user")
public class ProjectUser {
    @EmbeddedId
    private ProjectUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name = "role")
    private ROLE role;

    public enum ROLE{
        DEVELOPER, QUALITY_AGENT
    }

    public ProjectUser() {
    }

    public ProjectUser(Project project, User user) {
        this.project = project;
        this.user = user;
        this.id = new ProjectUserId(project.getId(), user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectUser that = (ProjectUser) o;
        return Objects.equals(id, that.id) && Objects.equals(project, that.project) && Objects.equals(user, that.user) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, user, role);
    }
}
