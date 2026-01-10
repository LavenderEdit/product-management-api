package studios.tkoh.producto_manager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studios.tkoh.producto_manager.dto.PersonDTO;
import studios.tkoh.producto_manager.model.Movement;

/**
 *
 * @author Studios TKOH!
 */
@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByProductIdOrderByDateDesc(Long productId);

    @Query("SELECT DISTINCT new studios.tkoh.producto_manager.dto.PersonDTO(m.receiverName, m.receiverRole) "
            + "FROM Movement m "
            + "WHERE (LOWER(m.receiverName) LIKE LOWER(CONCAT('%', :query, '%')) "
            + "OR LOWER(m.receiverRole) LIKE LOWER(CONCAT('%', :query, '%'))) "
            + "AND m.receiverName IS NOT NULL")
    List<PersonDTO> searchPersonnel(@Param("query") String query);
}
