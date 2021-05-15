package business.concretes;

import business.abstracts.AuthService;
import business.abstracts.CustomerService;
import core.Regex;
import entities.concretes.Customer;
import entities.concretes.customerDataTransfer;

public class AuthManager implements AuthService{
	private CustomerService customerService;

	public AuthManager(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Override
	public void register(Customer customer) {
		if (customerCheckIfNull(customer) && customerExists(customer.geteMail())
				&& checkIfPassword(customer.getPassword()) && Regex.emailValidate(customer.geteMail())) {
			customerService.add(customer);
		} else {
			System.out.println("Kay�t Ba�ar�s�z!");
		}
	}

		@Override
		public void login(customerDataTransfer customerDto) {
			Customer customer = customerService.getByMail(customerDto.getEmail());

			if (customer != null && customer.getPassword().equals(customerDto.getPassword())
					&& loginNullControl(customerDto)) {
				System.out.println("Giri� Ba�ar�l�!");
			} else {
				System.out.println("Giri� Ba�ar�s�z!");
			}

		}

		@Override
		public boolean customerExists(String email) {
			if (customerService.getByMail(email) != null) {
				System.out.println("Kullan�c� Mevcut!");
				return false;
			}
			return true;

		}

		public boolean checkIfPassword(String password) {
			if (password.length() < 6) {

				System.out.println("Parola 6 karakterden b�y�k olmal�d�r!");
				return false;
			}

			return true;
		}

		public boolean customerCheckIfNull(Customer customer) {
			if (customer != null && !customer.getName().isEmpty() && !customer.getSurname().isEmpty()
					&& !customer.geteMail().isEmpty() && !customer.getPassword().isEmpty()) {
				return true;
			}
			System.out.println("Bu alanlar bo� b�rak�lamaz!");
			return false;
		}

		public boolean loginNullControl(customerDataTransfer customerDto) {
			if (!customerDto.getEmail().isEmpty() && !customerDto.getPassword().isEmpty()) {
				return true;
			}
			System.out.println("Bu alanlar bo� b�rak�lamaz!");
			return false;
		}
	}