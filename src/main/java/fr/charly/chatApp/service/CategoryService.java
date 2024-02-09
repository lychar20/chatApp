package fr.charly.chatApp.service;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.repository.CategoryRepository;
import fr.charly.chatApp.service.interfaces.DAOFindAllInterface;
import fr.charly.chatApp.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements DAOFindAllInterface<Category>, DAOFindBySlugInterface<Category> {


    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllSorted() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Category findBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }
}
