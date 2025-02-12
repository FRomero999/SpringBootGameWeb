package org.example.gamesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired JuegoRepository juegoRepository;
    @Autowired UserRepository userRepository;
    @Autowired SecurityService securityService;

    @GetMapping("/")
    public List<Juego> all(){
        return juegoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> findById(@PathVariable String id){
        if(juegoRepository.existsById(id)){
            var juego = juegoRepository.findById(id).get();
            return( new ResponseEntity<Juego>(juego, HttpStatus.OK) );
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/genero/{genero}")
    public List<Juego> findByGenero(@PathVariable String genero){
        return juegoRepository.findJuegosByGenero(genero);
    }

    @GetMapping("/retro/{año}")
    public List<Juego> retro(@PathVariable Integer año){
        return juegoRepository.findByAñoLessThan(año);
    }

    @GetMapping("/año/{año}")
    public List<Juego> año(@PathVariable Integer año){
        return juegoRepository.juegoDeAño(año);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id, @RequestParam String token){
        if(securityService.requestValidation(token) ) {
            juegoRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Juego> create(@RequestBody Juego juego){
        if(juegoRepository.existsById(juego.get_id())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else {
            juegoRepository.save(juego);
            return new ResponseEntity<>(juego, HttpStatus.CREATED);
        }
    }

}
