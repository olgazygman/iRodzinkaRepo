package controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@RepositoryRestResource(collectionResourceRel = "lista", path = "lista")
public interface ListaRepository extends PagingAndSortingRepository<Lista, Long> {
	List<Lista> findByGrupa_Nazwa(@Param("grupa") String nazwa);
	List<Lista> findByKiedyBetween(@Param("od") @DateTimeFormat(iso = ISO.DATE) Date date1, 
			@Param("do") @DateTimeFormat(iso = ISO.DATE) Date date2);
	List<Lista> findByGrupa_NazwaAndKiedyBetween(@Param("grupa") String nazwa, @Param("od") @DateTimeFormat(iso = ISO.DATE) Date date1, 
			@Param("do") @DateTimeFormat(iso = ISO.DATE) Date date2);
}
