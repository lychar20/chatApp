package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, EntityNomenclatureRepository<Category> {

}
