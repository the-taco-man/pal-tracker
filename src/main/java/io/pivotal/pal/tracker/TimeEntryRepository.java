package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry update(long eq, TimeEntry any);

    void delete(long timeEntryId);

    List<TimeEntry> list();

    TimeEntry find(long nonExistentTimeEntryId);

    TimeEntry create(TimeEntry timeEntryToCreate);
}
