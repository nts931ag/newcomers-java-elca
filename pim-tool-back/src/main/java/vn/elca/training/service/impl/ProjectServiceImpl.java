package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author vlp
 *
 */
@Service
@Profile("!dummy | dev")
public class ProjectServiceImpl implements ProjectService {


    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Project maintainProjectById(Long id) {
        var oldProject = projectRepository.findById(id).orElseThrow();

        var newProject = new Project(
                oldProject.getName()+" Maint. " + LocalDate.now().getYear()
                ,oldProject.getFinishingDate());
        newProject.setActive(true);

        projectRepository.save(newProject);
        oldProject.setActive(false);

        var a = false;
        if(a == false){
            throw  new RuntimeException("Exception here");
        }
        return newProject;
    }

}
