package fr.charly.chatApp.service.interfaces;

public interface DAOFindByIdInterface<T> {

    T findById(Long id);

}
