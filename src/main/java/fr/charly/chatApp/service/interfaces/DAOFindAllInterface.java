package fr.charly.chatApp.service.interfaces;

import java.util.List;

public interface DAOFindAllInterface<T> {

    List<T> findAll();

    List<T> findAllSorted();

}
