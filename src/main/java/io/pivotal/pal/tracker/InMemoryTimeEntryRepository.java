package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    List<TimeEntry> timeEntries = new ArrayList<>();
    Long idIncrementValue = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        if ( this.find(timeEntryToCreate.getId()) == null ) {
            timeEntryToCreate.setId(idIncrementValue);
            idIncrementValue += 1;
            this.timeEntries.add(timeEntryToCreate);
        }
        return this.find(timeEntryToCreate.getId());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        for(TimeEntry entry : this.timeEntries) {
            if(entry.getId() == eq){
                any.setId(entry.getId());
                this.timeEntries.set(this.timeEntries.indexOf(entry), any);
                return any;
            }
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        for(TimeEntry entry : this.timeEntries){
            if(entry.getId() == timeEntryId){
                this.timeEntries.remove(entry);
                return;
            }
        }
    }

    @Override
    public List<TimeEntry> list() {
        return this.timeEntries;
    }

    @Override
    public TimeEntry find(long nonExistentTimeEntryId) {
        for (TimeEntry entry : this.timeEntries) {
            if (entry.getId() == nonExistentTimeEntryId) {
                return entry;
            }
        }
        return null;
    }
}
