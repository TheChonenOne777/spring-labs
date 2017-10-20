package lab.service;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository is more convenient declaration for such a class than general @Service
@Repository
@Transactional
public class CountryServiceImpl implements CountryService {


    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<Country> getAllCountriesInsideTransaction(
            Propagation propagation) {
        if (Propagation.REQUIRED.equals(propagation)) {
            return getAllCountriesRequired();
        } else if (Propagation.REQUIRES_NEW.equals(propagation)) {
            return getAllCountriesRequiresNew();
        } else if (Propagation.SUPPORTS.equals(propagation)) {
            return getAllCountriesSupports();
        } else if (Propagation.NEVER.equals(propagation)) {
            return getAllCountriesNever();
        } else if (Propagation.MANDATORY.equals(propagation)) {
            return getAllCountriesMandatory();
        } else if (Propagation.NOT_SUPPORTED.equals(propagation)) {
            return getAllCountriesNotSupported();
        } else {
            return getAllCountries();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Country> getAllCountriesRequired() {
        return countryDao.getCountryList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Country> getAllCountriesRequiresNew() {
        return countryDao.getCountryList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Country> getAllCountriesSupports() {
        return countryDao.getCountryList();
    }

    @Transactional(propagation = Propagation.NEVER)
    public List<Country> getAllCountriesNever() {
        return countryDao.getCountryList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Country> getAllCountriesMandatory() {
        return countryDao.getCountryList();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Country> getAllCountriesNotSupported() {
        return countryDao.getCountryList();
    }

    public List<Country> getAllCountries() {
        return countryDao.getCountryList();
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

}