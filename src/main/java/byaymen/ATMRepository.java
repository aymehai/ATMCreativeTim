package byaymen;

import org.springframework.data.repository.CrudRepository;

public interface ATMRepository extends CrudRepository<ATM, Integer>{
	
	public ATM findOneByAccountNumber(int accountNumber);
	
}
