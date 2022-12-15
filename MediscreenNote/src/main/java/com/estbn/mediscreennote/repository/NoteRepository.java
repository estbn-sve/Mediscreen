package com.estbn.mediscreennote.repository;
import com.estbn.mediscreennote.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface NoteRepository extends MongoRepository<Note, String> {
    Optional<List<Note>> findByIdPatient(String id);
}
