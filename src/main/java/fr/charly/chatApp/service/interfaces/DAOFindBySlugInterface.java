package fr.charly.chatApp.service.interfaces;

public interface DAOFindBySlugInterface<T> {

    T findBySlug(String slug);

}
