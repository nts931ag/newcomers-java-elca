package vn.elca.training.repository;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.entity.User;

@ContextConfiguration(classes = {ApplicationWebConfig.class})
@RunWith(value=SpringRunner.class)
public class ProjectRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testCountAll() {
        projectRepository.save(new Project("KSTA", LocalDate.now()));
        projectRepository.save(new Project("LAGAPEO", LocalDate.now()));
        projectRepository.save(new Project("ZHQUEST", LocalDate.now()));
        projectRepository.save(new Project("SECUTIX", LocalDate.now()));
        Assert.assertEquals(9, projectRepository.count());
    }

    @Test
    public void testFindOneWithQueryDSL() {
        final String PROJECT_NAME = "KSTA";
        projectRepository.save(new Project(PROJECT_NAME, LocalDate.now()));
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq(PROJECT_NAME))
                .fetchFirst();
        Assert.assertEquals(PROJECT_NAME, project.getName());
    }

    @Test
    public void verifyTheSavingOfOneProject(){
        var objectTransient = new Project("NTS", LocalDate.now());
        projectRepository.save(objectTransient);
        var objectPersistent = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq("NTS"))
                .fetchOne();

        Assert.assertEquals(objectTransient.getName(), objectPersistent.getName());
    }

    @Test
    public void verifyTheDeletionOfAProject(){
        // count number of current project in db
        long nboProject = projectRepository.count();
        Assert.assertEquals(5, nboProject);

        // delete one current project in db
        final var idProject = 2L;
        projectRepository.deleteById(idProject);

        // check number of current project in db
        nboProject = projectRepository.count();
        Assert.assertEquals(4, nboProject);
    }

    @Test
    public void verifyQueryDslBasedOnProjectAttributes(){
        final String PROJECT_NAME = "KSTA";
        final LocalDate PROJECT_TIME = LocalDate.now();
        projectRepository.save(new Project(PROJECT_NAME, PROJECT_TIME));
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq(PROJECT_NAME).and(QProject.project.finishingDate.eq(PROJECT_TIME)))
                .fetchFirst();
        Assert.assertEquals(PROJECT_NAME, project.getName());
    }

    @Test
    public void verifyTheSavingOfMultipleProjects(){
        // create user and set group leader
        User groupLeader = new User("HNH", "HNH");
        Group group = new Group(groupLeader);

        // create member

        var memeber1 = new User("HPN", "HPN");
        var memeber2 = new User("HUN", "HUN");
        var memeber3 = new User("BNN", "BNN");
        var memeber4 = new User("PNH", "PNH");
        var memeber5 = new User("QMV", "QMV");
        var memeber6 = new User("VVT", "VVT");

        //

    }

}
