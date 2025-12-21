import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.ServiceEntry;

@Repository
public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {

    List<ServiceEntry> findByVehicleId(Long vehicleId);

    List<ServiceEntry> findByGarageId(Long garageId);
    ServiceEntry findTopByVehicleIdOrderByServiceDateDesc(Long vehicleId);
}
