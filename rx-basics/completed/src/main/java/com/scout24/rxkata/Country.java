package com.scout24.rxkata;

class Country {

  private final String name;
  private final String currency;
  private final long population;

  Country(String name, String currency, long population) {
    this.name = name;
    this.currency = currency;
    this.population = population;
  }

  String name() {
    return name;
  }

  String currency() {
    return currency;
  }

  long population() {
    return population;
  }

  @Override
  public String toString() {
    return "Country{" +
        "name='" + name + '\'' +
        ", currency='" + currency + '\'' +
        ", population=" + population +
        '}';
  }
}
