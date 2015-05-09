package controller;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "grupa", path = "grupa")
public interface GrupaRepository extends PagingAndSortingRepository<Grupa, Long> {
	List<Grupa> findByNazwa(@Param("nazwa") String nazwa);
}
