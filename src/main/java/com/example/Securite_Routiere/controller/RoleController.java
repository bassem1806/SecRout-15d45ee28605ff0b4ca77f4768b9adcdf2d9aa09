package com.example.Securite_Routiere.controller;

import com.example.Securite_Routiere.entities.Role;
import com.example.Securite_Routiere.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("list")
    public String listRoles(Model model) {

        List<Role> roles = roleRepository.findAll();
        long nbr = roleRepository.count();
        if (roles.size() == 0)
            roles = null;
        model.addAttribute("roles", roles);
        model.addAttribute("nbr", nbr);
        return "role/listRoles";
    }

    @GetMapping("add")
    public String showAddRoleForm(Model model) {

        //m.addAttribute("Role",new Role("Admin"));
        Role role = new Role();
        model.addAttribute("role", role);

        return "role/addRole";
    }

    @PostMapping("add")
    public String addRole(@RequestParam("Role") String roleName) {

        System.out.println(roleName);
        Role r = new Role(roleName);
        Role rSaved = roleRepository.save(r);
        System.out.println("role = " + rSaved);
        return "redirect:list";
    }


}