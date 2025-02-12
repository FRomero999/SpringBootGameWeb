package org.example.gamesapi;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
@Data
public class User {
    @Id
    private String _id;
    private String user;
    private String email;
    private String token;
}

