package controller;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "uzytkownik", path = "uzytkownik")
public interface UzytkownikRepository extends PagingAndSortingRepository<Uzytkownik, Long> {
	List<Uzytkownik> findByGrupa_Nazwa(@Param("grupa") String grupa);
	List<Grupa> findByImie(@Param("imie") String imie);
}
