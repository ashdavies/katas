package com.scout24.rxkata;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class CountriesServiceTest {

  private CountriesService service;
  private List<Country> countries;

  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);

  @Before
  public void setUp() {
    service = new CountriesServiceImpl();
    countries = CountriesTestProvider.countries();
  }

  @Test
  public void shouldReturnCountryNameUppercase() {
    Country country = CountriesTestProvider.countries().get(0);

    service
        .getCountryNameUppercase(country)
        .test()
        .assertNoErrors()
        .assertValue(country.name().toUpperCase(Locale.GERMANY));
  }

  @Test
  public void shouldReturnNumberOfCountries() {
    Integer expected = CountriesTestProvider.countries().size();

    service
        .getCountryCount(countries)
        .test()
        .assertNoErrors()
        .assertValue(expected);
  }

  @Test
  public void shouldReturnPopulationOfCountries() {
    List<Long> expected = CountriesTestProvider.populationOfCountries();

    service
        .getCountryPopulations(countries)
        .test()
        .assertValueSet(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldListCountryNames() {
    List<String> expected = CountriesTestProvider.countryNames();

    service
        .getCountryNames(countries)
        .test()
        .assertValueSet(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldListThirdAndFourthCountry() {
    List<Country> expected = new ArrayList<>();
    expected.add(countries.get(2));
    expected.add(countries.get(3));

    service
        .getThirdFourthCountries(countries)
        .test()
        .assertValueSet(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldReturnIfAllCountriesGreaterThanOneMillion() {
    List<Country> expected = CountriesTestProvider.countriesWithPopulationGreaterThanOneMillion();

    service
        .areCountryPopulationsGreaterThan(expected, 1000000)
        .test()
        .assertResult(true)
        .assertNoErrors();
  }

  @Test
  public void shouldReturnFalseIfCountryLessThanOneMillion() {
    service
        .areCountryPopulationsGreaterThan(countries, 1000000)
        .test()
        .assertResult(false)
        .assertNoErrors();
  }

  @Test
  public void shouldReturnCountriesGreaterThanOneMillion() {
    List<Country> expected = CountriesTestProvider.countriesWithPopulationGreaterThanOneMillion();

    service
        .getCountriesWithPopulationGreaterThan(countries, 1000000)
        .test()
        .assertValueSet(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldReturnPopulationsGreaterThanOneMillion() {
    List<Country> expected = CountriesTestProvider.countriesWithPopulationGreaterThanOneMillion();

    FutureTask<List<Country>> task = new FutureTask<>(() -> {
      TimeUnit.MILLISECONDS.sleep(100);
      return countries;
    });

    new Thread(task).start();

    TestObserver<Country> observer = service
        .getCountryWithPopulationGreaterThan(task, 1000000,1, TimeUnit.SECONDS)
        .test();

    observer.awaitTerminalEvent();

    observer.assertComplete()
        .assertValueSet(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldReturnEmptyOnPopulationsGreaterThanOneMillionTimeout() {
    FutureTask<List<Country>> futureTask = new FutureTask<>(() -> {
      TimeUnit.HOURS.sleep(1);
      return countries;
    });

    new Thread(futureTask).start();

    TestObserver<Country> observer = service
        .getCountryWithPopulationGreaterThan(futureTask, 1000000,1, TimeUnit.SECONDS)
        .test();

    observer.awaitTerminalEvent();

    observer.assertComplete()
        .assertNoValues()
        .assertNoErrors();
  }

  @Test
  public void shouldGetCountryCurrency() {
    service
        .getCurrencyWithDefault("Austria", countries)
        .test()
        .assertResult("EUR")
        .assertNoErrors();
  }

  @Test
  public void shouldGetDefaultCountryCurrency() {
    service
        .getCurrencyWithDefault("Senegal", countries)
        .test()
        .assertResult("GBP")
        .assertNoErrors();
  }

  @Test
  public void shouldReturnSumPopulationOfCountries() {
    // hint: use "reduce" operator
    service
        .getTotalPopulation(countries)
        .test()
        .assertResult(CountriesTestProvider.sumPopulationOfAllCountries())
        .assertNoErrors();
  }

  @Test
  public void shouldReturnCountryPopulationMap() {
    TestObserver<Map<String, Long>> values = service.getCountryPopulationMap(countries).test();
    Map<String, Long> expected = new HashMap<>();

    for (Country country : countries) {
      expected.put(country.name(), country.population());
    }

    values.assertResult(expected);
    values.assertNoErrors();
  }

  @Test
  public void shouldReturnSumOfCountryPopulation() {
    long expected = CountriesTestProvider.sumPopulationOfAllCountries() + CountriesTestProvider.sumPopulationOfAllCountries();

    // hint: use "map" operator
    service
        .getTotalPopulation(Observable.fromIterable(countries), Observable.fromIterable(countries))
        .test()
        .assertResult(expected)
        .assertNoErrors();
  }

  @Test
  public void shouldCheckObservablesAreEqual() {
    // hint: use "sequenceEqual" operator
    service
        .areObservablesEqual(Observable.fromIterable(countries), Observable.fromIterable(countries))
        .test()
        .assertResult(true)
        .assertNoErrors();
  }

  @Test
  public void shouldCheckObservablesNotEqual() {
    List<Country> inverted = new ArrayList<>(countries);
    Collections.swap(inverted, 0, 1);

    service.areObservablesEqual(Observable.fromIterable(countries), Observable.fromIterable(inverted))
        .test()
        .assertResult(false)
        .assertNoErrors();
  }
}
