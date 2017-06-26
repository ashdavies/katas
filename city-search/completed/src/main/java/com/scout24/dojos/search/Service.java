package com.scout24.dojos.search;

import io.reactivex.Single;
import java.util.List;

public interface Service {

  Single<List<Cities.City>> search(String input);
}
