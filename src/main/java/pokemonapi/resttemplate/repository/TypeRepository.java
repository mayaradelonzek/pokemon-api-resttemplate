package pokemonapi.resttemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokemonapi.resttemplate.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
}
