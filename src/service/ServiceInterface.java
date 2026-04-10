package service;

import domain.Entityinterface;

import java.util.UUID;

public interface ServiceInterface {
    void create(Entityinterface entity);
    void delete(Entityinterface entity);
    void listAll();
    Entityinterface getByUd(UUID id);
}
