package org.example.gamesapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JuegoRepository extends MongoRepository<Juego, String> {
    public List<Juego> findJuegosByGenero(String genero);
    public List<Juego> findByAñoLessThan(Integer año);

    @Query("{'año':?0}")
    public List<Juego> juegoDeAño(Integer año);

}
