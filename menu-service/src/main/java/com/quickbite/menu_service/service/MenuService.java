package com.quickbite.menu_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quickbite.menu_service.dto.MenuItemRequest;
import com.quickbite.menu_service.dto.MenuItemResponse;
import com.quickbite.menu_service.entity.MenuItem;
import com.quickbite.menu_service.exception.ResourceNotFoundException;
import com.quickbite.menu_service.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

private final MenuRepository menuRepository;

    // Obtener menú y transformarlo a DTO
    public List<MenuItemResponse> getAvailableMenu() {
        return menuRepository.findByAvailableTrue()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Crear plato desde un DTO
    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItem item = new MenuItem();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setCategory(request.getCategory());
        item.setAvailable(true); // Por defecto disponible

        MenuItem savedItem = menuRepository.save(item);
        return mapToResponse(savedItem);
    }

    // Ejemplo de buscar por ID y lanzar la excepción
    public MenuItemResponse getMenuItemById(Long id) {
        MenuItem item = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con el ID: " + id));
        return mapToResponse(item);
    }

    // Método auxiliar para transformar Entity a DTO
    private MenuItemResponse mapToResponse(MenuItem item) {
        return MenuItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .category(item.getCategory())
                .available(item.isAvailable())
                .build();
    }

}
