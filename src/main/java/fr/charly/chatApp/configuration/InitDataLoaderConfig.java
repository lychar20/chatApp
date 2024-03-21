package fr.charly.chatApp.configuration;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.interfaces.NomenclatureInterface;
import fr.charly.chatApp.repository.CategoryRepository;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    private Slugger slugger;



    @Override
    public void run(String... args) throws Exception {
        createCategory();
        categoryRepository.flush();
    }

    private void createCategory() {
        createNomenclatures(
                categoryRepository,
                Category.class,
                List.of("Professionnel", "Parentalité", "Personnel", "Informatique", "Outils_de_Maison", "Bricolage", "Jardinage", "Cuisine", "Sport")
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items
    ) {
        items.forEach((name) -> {
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }



}
