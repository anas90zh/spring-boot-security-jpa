package bitsTracker.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bitsTracker.entity.bitUser.BitUser;

@Repository
public interface BItUserRepository extends JpaRepository<BitUser, Long> {

	Optional<BitUser> findByUserName(String userName);

	Optional<BitUser> findByEmail(String email);

}
