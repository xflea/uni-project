package it.logicainformatica.uniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.logicainformatica.uniproject.model.Corso;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, String> {

}