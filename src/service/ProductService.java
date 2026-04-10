package service;

import adapter.DatabaseStorage;
import adapter.PersistInterface;
import domain.Entityinterface;

import java.util.ArrayList;
import java.util.UUID;

public class ProductService implements ServiceInterface{
    PersistInterface armazenamento = new DatabaseStorage();


    @Override
    public void create(Entityinterface entity) {
        this.armazenamento.save(entity);
    }

    @Override
    public void delete(Entityinterface entity) {
        this.armazenamento.delete(entity);
    }

    @Override
    public void listAll() {
        ArrayList<Entityinterface> dados = armazenamento.listAll();
        for (int i = 0; i < dados.size(); i++) {
            System.out.println(dados.get(i));
        }
    }

    @Override
    public Entityinterface getByUd(UUID id) {
        return armazenamento.findOneById(id);
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }
}
