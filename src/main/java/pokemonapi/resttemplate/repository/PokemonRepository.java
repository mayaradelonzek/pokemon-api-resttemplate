package pokemonapi.resttemplate.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokemonapi.resttemplate.model.Pokemon;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @EntityGraph(attributePaths = {"type1", "type2", "moves"})
    Optional<Pokemon> findByIdAndActive(Integer id, Boolean active);

    @EntityGraph(attributePaths = {"type1", "type2", "moves"})
    Optional<Pokemon> findByNameAndActive(String name, Boolean active);

    @EntityGraph(attributePaths = {"type1", "type2", "moves"})
    List<Pokemon> findByActiveAndWeightGreaterThan(Boolean active, int weight);

}
