package data.repositories;

import data.model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        entry1.setId(entry1.getId());
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
    @Test
    public void testThatWhenEntryThatDoesNotExistIsFound_nullIsReturned(){
        assertNull(entryRepositoriesImplement.findById(1));
    }
    @Test
    public void testThatAllEntryCanBeFound(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");
        entryRepositoriesImplement.save(entry);

        Entry entry1 = new Entry();
        entry1.setId(entry1.getId());
        entry1.setTitle("title");
        entry1.setBody("body");
        entryRepositoriesImplement.save(entry1);
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry1);
        assertEquals(entries,entryRepositoriesImplement.findAll());
    }
    @Test
    public void testThatEntryCanBeDeletedByEntryObject(){
        Entry entry = new Entry();
        entry.setId(entry.getId());
        entry.setTitle("title");
        entry.setBody("body");
        entryRepositoriesImplement.save(entry);

        entryRepositoriesImplement.delete(entry);
        assertEquals(0,entryRepositoriesImplement.count());
    }
}