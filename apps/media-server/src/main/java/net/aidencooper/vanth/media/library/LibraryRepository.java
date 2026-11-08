package net.aidencooper.vanth.media.library;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface LibraryRepository extends ListCrudRepository<Library, Long> {
    List<Library> findAllByOrderByIdAsc();
}
