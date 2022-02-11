package application.Models;

public class Account {
	private String username;
	private String password;
	private String permission;
	
	public Account() {
		super();
	}
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Account(String username, String password, String permission) {
		super();
		this.username = username;
		this.password = password;
		this.permission = permission;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public boolean equals(Object obj) {
		Account acc = (Account) obj;
		if(this.username.equals(acc.username) && this.password.equals(acc.password))
			return true;
		return false;
	}
	
}
