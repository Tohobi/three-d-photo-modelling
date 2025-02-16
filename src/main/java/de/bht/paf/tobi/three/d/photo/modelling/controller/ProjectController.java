package de.bht.paf.tobi.three.d.photo.modelling.controller;

import de.bht.paf.tobi.three.d.photo.modelling.model.Project;
import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.repository.ProjectRepository;
import de.bht.paf.tobi.three.d.photo.modelling.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // Eigene Projekte anzeigen
    @GetMapping("/my-projects")
    public String viewMyProjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            List<Project> projects = projectRepository.findByOwner(user.get());
            model.addAttribute("projects", projects);
            return "my-projects";
        } else {
            return "redirect:/login";
        }
    }

    // Projekte anderer User anzeigen
    @GetMapping("/other-projects")
    public String viewOtherProjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            List<Project> projects = projectRepository.findByOwnerNot(user.get());
            model.addAttribute("projects", projects);
            return "other-projects";
        } else {
            return "redirect:/login";
        }
    }
}
