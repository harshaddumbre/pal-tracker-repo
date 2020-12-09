package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {


    TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry result = timeEntryRepository.create(timeEntry);
        ResponseEntity<TimeEntry> response = new ResponseEntity<>(result, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id,@RequestBody TimeEntry timeEntry){
        TimeEntry result = timeEntryRepository.update(id,timeEntry);

        if(result == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void>  delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry result = timeEntryRepository.find(id);

        if(result == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> result = timeEntryRepository.list();
        ResponseEntity<List<TimeEntry>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
}

