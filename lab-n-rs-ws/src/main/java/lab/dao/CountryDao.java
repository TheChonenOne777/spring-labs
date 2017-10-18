package lab.dao;

import lab.model.Country;

import java.util.List;

public interface CountryDao {

	void save(Country country);

	List<Country> getAllCountries();

	Country getCountryByName(String name);

}