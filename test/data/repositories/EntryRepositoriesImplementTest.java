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
        assertEquals(1,entryRepositoriesImplement.count());

    }
    @Test
    public void testThatICanAddMultipleEntries(){
        Entry entry = new Entry();
        Entry entry1 = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");
        entryRepositoriesImplement.save(entry);

        entry1.setId(entry.getId());
        entry1.setTitle("title1");
        entry1.setBody("body1");
        entryRepositoriesImplement.save(entry1);
        assertEquals(2,entryRepositoriesImplement.count());
    }
    @Test
    public void testThatEntryCanBeUpdated(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");
        entryRepositoriesImplement.save(entry);

        entry.setId(entry.getId());
        entry.setTitle("newTitle");
        entry.setBody("newBody");
        entryRepositoriesImplement.save(entry);
        assertEquals(1,entryRepositoriesImplement.count());

    }
    @Test
    public void testThatEntryCanBeDeleted(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");

        entryRepositoriesImplement.save(entry);
        entryRepositoriesImplement.delete(1);
        assertEquals(0,entryRepositoriesImplement.count());
    }
    @Test
    public void testThatEntryCanBeFoundWithEntryId(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");

        entryRepositoriesImplement.save(entry);
        assertEquals(entry,entryRepositoriesImplement.findById(1));
    }
}