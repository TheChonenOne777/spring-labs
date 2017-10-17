package lab.dao;

import java.util.List;

import lab.model.Country;

public interface CountryDao {

	public abstract void save(Country country);

	public abstract List<Country> getAllCountries();

	public abstract Country getCountryByName(String name);

}