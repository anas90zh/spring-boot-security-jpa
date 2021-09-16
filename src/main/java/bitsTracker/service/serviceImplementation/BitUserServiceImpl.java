package bitsTracker.service.serviceImplementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bitsTracker.entity.bitUser.BitRole;
import bitsTracker.entity.bitUser.BitUser;
import bitsTracker.entity.bitUser.BitUserRegistrationDto;
import bitsTracker.entity.bitUser.Role;
import bitsTracker.repos.BItUserRepository;
import bitsTracker.service.interfaces.BitUserService;

@Service
public class BitUserServiceImpl implements BitUserService {

	@Autowired
	private BItUserRepository bitUserRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<BitUser> getAllBitUsers() {
		return bitUserRepo.findAll();
	}

	@Override
	public BitUser saveBitUser(BitUserRegistrationDto bitUserDto ,String role ) {
		BitUser bitUser = new BitUser(bitUserDto.getUserName(),bitUserDto.getName(),bitUserDto.getEmail()//
				,passwordEncoder.encode(bitUserDto.getPassword()),
				Arrays.asList(new BitRole(role)) ) ;		
		return bitUserRepo.save(bitUser);
	}
	



	@Override
	public BitUser getBitUserByID(Long id) {
		BitUser bitUser = bitUserRepo.findById(id).//
				orElseThrow(() -> new RuntimeException("BitUser not found id : " + id));

		return bitUser;
	}


	@Override
	public BitUser createBitUser(BitUser bitUser) {
		BitUser newBitUser = bitUserRepo.save(bitUser);

		return newBitUser;
	}

	@Override
	public void deleteBitUser(Long id) {
		bitUserRepo.deleteById(id);
	}

	@Override
	public Page<BitUser> findPaginated(int pageNo, int PageSize, String sortField, String sortDircetion) {
		Sort sort = sortDircetion.equalsIgnoreCase(Sort.Direction.ASC.name()) ? //
				Sort.by(sortField).ascending() : Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, PageSize, sort);
		return bitUserRepo.findAll(pageable);
	}


	@Override
	public BitUser findByUserName(String userName) {
		BitUser bitUser = bitUserRepo.findByUserName(userName).//
				orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return bitUser;
	}


	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		BitUser bitUser = bitUserRepo.findByUserName(userName).//
				orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
		return new User(bitUser.getEmail(), bitUser.getPassword(),mapRolesAuthorities(bitUser.getRoles()));
	}
	
	private Collection<?extends GrantedAuthority> mapRolesAuthorities(Collection<BitRole> collection){
		return collection.stream().map( e -> new SimpleGrantedAuthority(e.getRole())).collect(Collectors.toList());
		
	}
	

}
