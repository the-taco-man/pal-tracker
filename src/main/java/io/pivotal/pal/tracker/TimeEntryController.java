package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(path = "/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(this.timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping(path = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entry = this.timeEntryRepository.find(timeEntryId);
        HttpStatus status = getHttpStatusFromEntity(entry);
        return new ResponseEntity(entry, status);
    }

    @GetMapping(path = "/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping(path = "/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry entry = this.timeEntryRepository.update(timeEntryId, expected);
        HttpStatus status = getHttpStatusFromEntity(entry);

        return new ResponseEntity(entry, status);
    }

    @DeleteMapping(path = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
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
