package vn.elca.training.repository;


import com.querydsl.jpa.impl.JPAQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.dao.GroupRepository;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@ContextConfiguration(classes = {ApplicationWebConfig.class})
@RunWith(value= SpringRunner.class)
public class GroupRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCountAll() {
        /*groupRepository.save(new Project("KSTA", LocalDate.now()));
        groupRepository.save(new Project("LAGAPEO", LocalDate.now()));
        groupRepository.save(new Project("ZHQUEST", LocalDate.now()));
        groupRepository.save(new Project("SECUTIX", LocalDate.now()));
        Assert.assertEquals(9, projectRepository.count());*/
        var user = userRepository.findUserByUsername("USER1");
        var group = new Group(user);
        groupRepository.save(group);
        Assert.assertEquals(1, groupRepository.count());
    }

    @Test
    public void testFindOneWithQueryDSL() {
        /*final String PROJECT_NAME = "KSTA";
        projectRepository.save(new Project(PROJECT_NAME, LocalDate.now()));
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq(PROJECT_NAME))
                .fetchFirst();
        Assert.assertEquals(PROJECT_NAME, project.getName());*/
        Long count = new JPAQuery<Group>(em)
                .from(QProject.project).fetchCount();
    }


}