package lab.service;

import lab.model.Country;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountriesInsideTransaction(Propagation propagation);

    List<Country> getAllCountriesRequired();

    List<Country> getAllCountriesRequiresNew();

    List<Country> getAllCountriesSupports();

    List<Country> getAllCountriesNever();

    List<Country> getAllCountriesMandatory();

    List<Country> getAllCountriesNotSupported();

    List<Country> getAllCountries();
}
