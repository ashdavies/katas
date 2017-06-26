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
    return Single.just(country.name().toUpperCase(Locale.GERMANY));
  }

  public Single<Integer> getCountryCount(List<Country> countries) {
    return Single.just(countries.size());
  }

  public Observable<Long> getCountryPopulations(List<Country> countries) {
    return Observable.fromIterable(countries)
        .map(Country::population);
  }

  @Override
  public Observable<String> getCountryNames(List<Country> countries) {
    return Observable.fromIterable(countries)
        .map(Country::name);
  }

  @Override
  public Observable<Country> getThirdFourthCountries(List<Country> countries) {
    return Observable.fromIterable(countries)
        .skip(2)
        .take(2);
  }

  @Override
  public Single<Boolean> areCountryPopulationsGreaterThan(List<Country> countries, long population) {
    return Observable.fromIterable(countries)
        .all(country -> country.population() > population);
  }

  @Override
  public Observable<Country> getCountriesWithPopulationGreaterThan(List<Country> countries, long population) {
    return Observable.fromIterable(countries)
        .filter(country -> country.population() > population);
  }

  @Override
  public Observable<Country> getCountryWithPopulationGreaterThan(FutureTask<List<Country>> task, long population, long timeout, TimeUnit unit) {
    return Observable.fromFuture(task, Schedulers.io())
        .flatMap(Observable::fromIterable)
        .filter(country -> country.population() > population)
        .timeout(timeout, TimeUnit.SECONDS, Observable.empty());
  }

  @Override
  public Observable<String> getCurrencyWithDefault(String name, List<Country> countries) {
    return Observable.fromIterable(countries)
        .filter(country -> country.name().equals(name))
        .map(Country::currency)
        .defaultIfEmpty("GBP");
  }

  @Override
  public Observable<Long> getTotalPopulation(List<Country> countries) {
    return Observable.fromIterable(countries)
        .map(Country::population)
        .reduce((l1, l2) -> l1 + l2)
        .toObservable();
  }

  @Override
  public Single<Map<String, Long>> getCountryPopulationMap(List<Country> countries) {
    return Observable.fromIterable(countries)
        .toMap(Country::name, Country::population);
  }

  @Override
  public Observable<Long> getTotalPopulation(Observable<Country> first, Observable<Country> second) {
    return Observable.merge(first, second)
        .map(Country::population)
        .reduce((l1, l2) -> l1 + l2)
        .toObservable();
  }

  @Override
  public Single<Boolean> areObservablesEqual(Observable<Country> first, Observable<Country> second) {
    return Observable.sequenceEqual(first, second);
  }
}
