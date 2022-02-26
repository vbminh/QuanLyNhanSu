package application.Models;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable{
	private String username;
	private String password;
	private String permission;
	
	public Account() {
		super();
	}
	
	public Account(String username) {
		super();
		this.username = username;
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
	
	public void setUsername(String username) throws Exception {
		if(username.trim().equals(""))
			throw new Exception("Ten khong duoc bo trong");
		this.username = username;
	}

	public void setPassword(String password) throws Exception {
		if(password.trim().equals(""))
			throw new Exception("Mat khau khong duoc bo trong");
		this.password = password;
	}

	public void setPermission(String permission) throws Exception {
		if(permission.trim().equals(""))
			throw new Exception("Quyen su dung khong duoc bo trong");
		this.permission = permission;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.username);
        return hash;
    }
	
}
