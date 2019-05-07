package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(TimeEntry timeEntryToCreate) {
        return new ResponseEntity(this.timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry entry = this.timeEntryRepository.find(timeEntryId);
        HttpStatus status = getHttpStatusFromEntity(entry);
        return new ResponseEntity(entry, status);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        TimeEntry entry = this.timeEntryRepository.update(timeEntryId, expected);
        HttpStatus status = getHttpStatusFromEntity(entry);

        return new ResponseEntity(entry, status);
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private HttpStatus getHttpStatusFromEntity(TimeEntry entry) {
        HttpStatus status;
        if (entry == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return status;
    }
}
