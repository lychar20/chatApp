
package fr.charly.chatApp.repository;

import java.util.Optional;

public interface EntityNameRepository<T> {

    Optional<T> findByName(String name);

}
