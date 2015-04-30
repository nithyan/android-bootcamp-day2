package android.bootcamp.androidbootcampday2.repository;

import android.bootcamp.androidbootcampday2.model.Name;
import android.bootcamp.androidbootcampday2.storage.DataStorage;
import android.content.Context;

import java.util.List;

public class NameRepository {

    DataStorage dataStorage;

    public NameRepository(Context context) {
        dataStorage = new DataStorage(context);
    }

    public void save(String name) {
        dataStorage.save(new Name(name));
    }

    public List<Name> getNames() {
        return dataStorage.getData();
    }
}
