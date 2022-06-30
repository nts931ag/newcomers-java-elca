package vn.elca.training.model.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "group_db")
public class Group {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "group_leader_id")
    private User groupLeader;

    @OneToMany(mappedBy = "group")
    private Set<Project> projectSet;

    public Group(User groupLeader) {
        this.groupLeader = groupLeader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(User groupLeader) {
        this.groupLeader = groupLeader;
    }

    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupLeader=" + groupLeader +
                ", projectSet=" + projectSet +
                '}';
    }
}
