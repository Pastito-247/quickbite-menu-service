package com.quickbite.menu_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quickbite.menu_service.entity.MenuItem;
import com.quickbite.menu_service.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuItem> getAvailableMenu() {
        return menuRepository.findByAvailableTrue();
    }

    public MenuItem createMenuItem(MenuItem item){
        if (item.getPrice() == null || item.getPrice() < 0) {
            throw new IllegalArgumentException("El precio del plato debe ser mayor a 0");
        }

        item.setAvailable(true);
        return menuRepository.save(item);
    }

    
}
