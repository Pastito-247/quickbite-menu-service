package com.quickbite.menu_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickbite.menu_service.entity.MenuItem;
import com.quickbite.menu_service.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    
    private final MenuService menuService;

    @GetMapping
    public List<MenuItem> showMenu() {
        return menuService.getAvailableMenu();
    }
    
    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem item){
        return menuService.createMenuItem(item);
    }
}
