package main.java.com.excilys.projet.java.cdb.models;

public class Company {
	
	private Long idCompany;
	private String name;
	
	public Company(Long idCompany, String name) {
		this.idCompany = idCompany;
		this.name = name;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCompany == null) ? 0 : idCompany.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (idCompany == null) {
			if (other.idCompany != null)
				return false;
		} else if (!idCompany.equals(other.idCompany))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public Company(CompanyBuilder builder) {
		this.idCompany = builder.idCompany;
		this.name = builder.name;
	}
	
	public static class CompanyBuilder{
		private long idCompany;
		private String name;
		
		public CompanyBuilder(long idCompany, String name) {
			this.idCompany = idCompany;
			this.name = name;
		}
		
		public Company build() {
			return new Company(this);
		}
	}
	
	
}
