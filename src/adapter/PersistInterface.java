package adapter;

import domain.Entityinterface;

import java.util.ArrayList;
import java.util.UUID;

public interface PersistInterface {
    void save(Entityinterface entity);
    void delete(Entityinterface entity);
    ArrayList<Entityinterface> listAll();
    Entityinterface findOneById(UUID id);

}
