package com.scout24.dojos.search;

import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AutoValue
abstract class Cities {

  static final Cities BERLIN = create(City.BERLIN);

  abstract List<City> cities();

  static Cities create(City... cities) {
    return create(Arrays.asList(cities));
  }

  static Cities create(List<City> cities) {
    return new AutoValue_Cities(new ArrayList<>(cities));
  }

  Cities add(City city) {
    List<City> cities = new ArrayList<>(cities());
    cities.add(city);

    return create(cities);
  }

  @AutoValue
  static abstract class City {

    static final City BERLIN = City.create("Berlin");
    static final City LONDON = City.create("London");

    abstract String name();

    static City create(String name) {
      return new AutoValue_Cities_City(name);
    }
  }
}
