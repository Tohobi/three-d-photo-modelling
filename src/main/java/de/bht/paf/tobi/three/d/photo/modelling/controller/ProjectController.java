package de.bht.paf.tobi.three.d.photo.modelling.controller;

import de.bht.paf.tobi.three.d.photo.modelling.model.Project;
import de.bht.paf.tobi.three.d.photo.modelling.model.Rating;
import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.repository.ProjectRepository;
import de.bht.paf.tobi.three.d.photo.modelling.repository.RatingRepository;
import de.bht.paf.tobi.three.d.photo.modelling.repository.UserRepository;
import de.bht.paf.tobi.three.d.photo.modelling.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final RatingService ratingService;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository, RatingRepository ratingRepository, RatingService ratingService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.ratingService = ratingService;
    }

    @GetMapping("/my")
    public String showUserProjects(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Project> projects = projectRepository.findByUser(user);
        model.addAttribute("projects", projects);
        return "my-projects";
    }

    @GetMapping("/others")
    public String showOtherProjects(Principal principal, Model model) {
        String currentUsername = principal.getName();
        List<Project> projects = projectRepository.findAll().stream()
                .filter(p -> !p.getUser().getUsername().equals(currentUsername))
                .toList();
        model.addAttribute("projects", projects);
        return "other-projects";
    }

    @GetMapping("/new")
    public String showNewProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "new-project";
    }

    @PostMapping("/save")
    public String saveNewProject(@ModelAttribute Project project, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        project.setUser(user);
        projectRepository.save(project);
        return "redirect:/projects/my";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        model.addAttribute("project", project);
        return "edit-project";
    }

    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable Long id, @RequestParam String name) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setName(name);
        projectRepository.save(project);
        return "redirect:/projects/my";
    }

    @GetMapping("/rate/{id}")
    public String rateProject(@PathVariable Long id, Model model, Principal principal) {
        Project project = projectRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();

        Optional<Rating> existingRating = ratingRepository.findByUserAndProject(user, project);
        if (existingRating.isPresent()) {
            model.addAttribute("error", "❌ Du hast dieses Projekt bereits bewertet!");
            return "rating-error";
        }

        model.addAttribute("project", project);
        model.addAttribute("newRating", new Rating());
        return "rate-project";
    }

    @PostMapping("/rate/{id}")
    public String submitRating(@PathVariable Long id, @ModelAttribute Rating newRating, Principal principal) {
        Project project = projectRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();

        // Überprüfen, ob bereits bewertet wurde
        Optional<Rating> existingRating = ratingRepository.findByUserAndProject(user, project);
        if (existingRating.isPresent()) {
            return "redirect:/projects/others?error=already_rated";
        }

        // Version initialisieren
        newRating.setVersion(0L);
        newRating.setProject(project);
        newRating.setUser(user);
        ratingRepository.save(newRating);

        return "redirect:/projects/others";
    }

    @GetMapping("/{projectId}/rate")
    public String showRatingForm(@PathVariable Long projectId, Principal principal, Model model) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();

        if (ratingService.hasUserAlreadyRated(user, project)) {
            model.addAttribute("error", "❌ Du hast dieses Projekt bereits bewertet!");
            return "rating-error";
        }

        model.addAttribute("rating", new Rating());
        model.addAttribute("projectId", projectId);
        return "rate-project";
    }

    @PostMapping("/{projectId}/rate")
    public String submitRating(@PathVariable Long projectId,
                               @ModelAttribute Rating rating,
                               Principal principal,
                               Model model) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();

        if (ratingService.hasUserAlreadyRated(user, project)) {
            model.addAttribute("error", "❌ Du hast dieses Projekt bereits bewertet!");
            return "rating-error";
        }

        rating.setUser(user);
        rating.setProject(project);
        ratingRepository.save(rating);

        return "redirect:/projects/others";
    }
}
