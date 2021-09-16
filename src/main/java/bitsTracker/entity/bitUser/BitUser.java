package bitsTracker.entity.bitUser;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
@Data
@NoArgsConstructor
public class BitUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	
	private String userName;
	
	private String email;


	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", //
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), //
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<BitRole> roles;

	public BitUser(String userName, String name, String email, String password, Collection<BitRole> roles) {
		super();
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}


}
