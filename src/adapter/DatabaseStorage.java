package adapter;

import domain.Entityinterface;

import java.util.ArrayList;
import java.util.UUID;

public class DatabaseStorage implements PersistInterface{
    private ArrayList<Entityinterface> db = new ArrayList<>();

    @Override
    public void save(Entityinterface entity) {
        db.add(entity);
    }

    @Override
    public void delete(Entityinterface entity) {
        db.remove(entity);
    }

    @Override
    public ArrayList<Entityinterface> listAll() {
        return db;
    }

    @Override
    public Entityinterface findOneById(UUID id) {
        //Percorrer a lista (db)
        for (int i = 0; i < db.size(); i++){

            if(db.get(i).getUUID().equals(id)) {
                return db.get(i);
            }
        }
        return null;
    }
}
