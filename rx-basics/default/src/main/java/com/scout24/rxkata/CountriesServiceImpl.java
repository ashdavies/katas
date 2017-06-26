package com.scout24.rxkata;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class CountriesServiceImpl implements CountriesService {

  @Override
  public Single<String> getCountryNameUppercase(Country country) {
    throw new NotImplementedException();
  }

  public Single<Integer> getCountryCount(List<Country> countries) {
    throw new NotImplementedException();
  }

  public Observable<Long> getCountryPopulations(List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<String> getCountryNames(List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<Country> getThirdFourthCountries(List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Single<Boolean> areCountryPopulationsGreaterThan(List<Country> countries, long population) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<Country> getCountriesWithPopulationGreaterThan(List<Country> countries, long population) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<Country> getCountryWithPopulationGreaterThan(FutureTask<List<Country>> task, long population, long timeout, TimeUnit unit) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<String> getCurrencyWithDefault(String name, List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<Long> getTotalPopulation(List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Single<Map<String, Long>> getCountryPopulationMap(List<Country> countries) {
    throw new NotImplementedException();
  }

  @Override
  public Observable<Long> getTotalPopulation(Observable<Country> first, Observable<Country> second) {
    throw new NotImplementedException();
  }

  @Override
  public Single<Boolean> areObservablesEqual(Observable<Country> first, Observable<Country> second) {
    throw new NotImplementedException();
  }
}
