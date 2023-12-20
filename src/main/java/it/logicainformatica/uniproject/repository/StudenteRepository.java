package it.logicainformatica.uniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.logicainformatica.uniproject.model.Studente;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, String> {

}