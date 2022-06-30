package vn.elca.training.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.entity.QTask;
import vn.elca.training.model.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author gtn
 *
 */
// Rename this class so that Spring can scan and wire this component correctly
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Project> findProjectsByTaskName(String taskName) {
        return new JPAQuery<Project>(em)
                .from(QProject.project)
                .innerJoin(QProject.project.tasks, QTask.task)
                .where(QTask.task.name.eq(taskName))
                .fetchJoin()
                .fetch();
    }

    @Override
    public List<Task> listRecentTasks(int limit) {
        return new JPAQuery<Task>(em)
                .from(QTask.task)
                .innerJoin(QTask.task.project)
                .orderBy(QTask.task.id.desc())
                .limit(limit)
                .fetchJoin()
                .fetch();
    }
}
