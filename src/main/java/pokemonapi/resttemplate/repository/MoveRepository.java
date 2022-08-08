package pokemonapi.resttemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokemonapi.resttemplate.model.Move;

@Repository
public interface MoveRepository extends JpaRepository<Move, Integer> {
}
