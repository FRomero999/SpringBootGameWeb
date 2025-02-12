package org.example.gamesapi;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="juegos")
@Data
public class Juego {
    @Id
    private String _id;
    private String nombre;
    private String genero;
    private Integer año;
    private String descripcion;
    private String imagen;
    private String[] plataformas;
}
