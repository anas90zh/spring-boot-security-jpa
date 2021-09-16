package bitsTracker.entity.bitUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class BitRole {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)	
	private Long id;
	private String role;
	
	
	public BitRole(String role) {
		this.role = role;
	}

}
