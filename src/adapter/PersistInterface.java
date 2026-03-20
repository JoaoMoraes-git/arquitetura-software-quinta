package adapter;

import domain.Entityinterface;

public interface PersistInterface {
    void save(Entityinterface entity);
    void delete();
}
