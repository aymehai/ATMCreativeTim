package byaymen;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATMController {

	@Autowired
	ATMRepository atmRepository;

	@RequestMapping("/")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/ATMHome")
	public String ATMHomePage() {
		return "ATMHome";
	}
	
	// Deposit Get and Post Mappings

	@GetMapping("/deposit")
	public String getDepositPage(Model model) {
		model.addAttribute(new ATM());
		return "deposit";
	}

	@PostMapping("/deposit")
	public String processDepositPage(@Valid ATM atm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "deposit";
		}

		atm.setAction("deposit");
		atm.updateBalance();

		atmRepository.save(atm);

		return "ATMHome";
	}

	// Withdraw Get and Post Mappings

	@GetMapping("/withdraw")
	public String withdrawPage(Model model) {
		model.addAttribute(new ATM());
		return "withdraw";
	}

	@PostMapping("/withdraw")
	public String processWithdrawPage(@Valid ATM atm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "withdraw";
		}

		atm.setAction("withdraw");
		atm.updateBalance();

		atmRepository.save(atm);

		return "ATMHome";
	}

	// Balance Get and Post Mappings

	@RequestMapping("/balance")
	public  String balancePage(Model model) {
		model.addAttribute("balanceList", atmRepository.findAll());
		return "balance";
	}

	// History Get and Post Mappings

	@RequestMapping("/history")
	public  String historyPage(Model model) {
		model.addAttribute("transList", atmRepository.findAll());
		return "history";
	}

}