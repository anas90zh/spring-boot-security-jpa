package bitsTracker.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import bitsTracker.entity.bitUser.BitRole;
import bitsTracker.entity.bitUser.BitUser;
import bitsTracker.entity.bitUser.BitUserRegistrationDto;

public interface BitUserService extends UserDetailsService {
	List<BitUser> getAllBitUsers();
	BitUser saveBitUser(BitUserRegistrationDto bitUserDto ,String role);
	void deleteBitUser(Long id);
	BitUser getBitUserByID(Long id);
	BitUser findByUserName(String userName);	
	BitUser createBitUser(BitUser bitUser);
	Page<BitUser> findPaginated(int pageNo, int PageSize, String sortField,String sortDircetion);
	
}
