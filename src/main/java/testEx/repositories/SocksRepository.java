package testEx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testEx.models.Sock;

@Repository
public interface SocksRepository extends JpaRepository<Sock, Integer> {

}
