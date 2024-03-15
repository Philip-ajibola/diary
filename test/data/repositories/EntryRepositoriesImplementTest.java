package data.repositories;

import data.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoriesImplementTest {
    private EntryRepositoriesImplement entryRepositoriesImplement;
    @BeforeEach
    public void createEntry(){
        entryRepositoriesImplement = new EntryRepositoriesImplement();
    }
    @Test
    public void testThatEntryCanBeCreated(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");
        entryRepositoriesImplement.save(entry);

    }
}