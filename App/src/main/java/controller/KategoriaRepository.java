package controller;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "kategoria", path = "kategoria")
public interface KategoriaRepository extends PagingAndSortingRepository<Kategoria, Long> {
	/*List<Person> findByLastName(@Param("name") String name); */
}
