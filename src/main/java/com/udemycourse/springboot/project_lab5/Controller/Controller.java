package com.udemycourse.springboot.project_lab5.Controller;

import com.udemycourse.springboot.project_lab5.ApiResponse.ApiResponse;
import com.udemycourse.springboot.project_lab5.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/projects")
public class Controller {
ArrayList<Project> projects = new ArrayList<>();


// 1 endpoint get
@GetMapping("/get")
public ArrayList<Project> getProjects() {
    return projects;
}


// 2 endpoint post
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
    projects.add(project);
    return new ApiResponse("Project added successfully");
    }


// 3 endpoint put
    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
    projects.set(index, project);
    return new ApiResponse("Project updated successfully");
    }

// 4 endpoint delete
@DeleteMapping("/delete/{index}")
 public ApiResponse deleteProject(@PathVariable int index) {
    projects.remove(index);
    return new ApiResponse("Project deleted successfully");
 }



 // 5 endpoint Change Status
 @PutMapping("/change-status/{index}")
 public ApiResponse changeStatus(@PathVariable int index) {
     if (projects.get(index).getStatus().equals("not done")) {
         projects.get(index).setStatus("done");
     } else {
         projects.get(index).setStatus("not done");
     }
     return new ApiResponse("Project status changed successfully");
 }

// 6 endpoint Search by Title
    @GetMapping("/search/{title}")
    public Project searchProject(@PathVariable String title) {
        for (Project p : projects) {
       if(p.getTitle().equals(title)) {
           return p;
        }
     }
           return null;
}

// 7 endpoint Filter by Company

    @GetMapping("/company/{name}")
    public ArrayList<Project> getProjectsByCompany(@PathVariable String name) {

        ArrayList<Project> result = new ArrayList<>();

        for (Project p : projects) {
            if (p.getCompanyName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }

        return result;
    }








}
