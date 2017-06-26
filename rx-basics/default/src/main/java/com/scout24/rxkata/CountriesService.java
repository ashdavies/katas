package com.scout24.rxkata;

import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

interface CountriesService {

  Single<String> getCountryNameUppercase(Country country);

  Single<Integer> getCountryCount(List<Country> countries);

  Observable<Long> getCountryPopulations(List<Country> countries);

  Observable<String> getCountryNames(List<Country> countries);

  Observable<Country> getThirdFourthCountries(List<Country> countries);

  Single<Boolean> areCountryPopulationsGreaterThan(List<Country> countries, long population);

  Observable<Country> getCountriesWithPopulationGreaterThan(List<Country> countries, long population);

  Observable<Country> getCountryWithPopulationGreaterThan(FutureTask<List<Country>> task, long population, long timeout, TimeUnit unit);

  Observable<String> getCurrencyWithDefault(String countryName, List<Country> countries);

  Observable<Long> getTotalPopulation(List<Country> countries);

  Observable<Long> getTotalPopulation(Observable<Country> first, Observable<Country> second);

  Single<Map<String, Long>> getCountryPopulationMap(List<Country> countries);

  Single<Boolean> areObservablesEqual(Observable<Country> first, Observable<Country> second);
}
