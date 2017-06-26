package com.scout24.dojos.search;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import java.io.IOException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class StreamInteractorTest {

  private Interactor streamInteractor;
  private Scheduler scheduler;

  @Mock Service service;
  @Mock StreamInteractor.Listener listener;

  @Before
  public void setUp() throws Exception {
    streamInteractor = new StreamInteractor(service, listener);
    scheduler = new TestScheduler();
  }

  @Test
  public void shouldStartWithIdleState() throws Exception {
    then(listener).should().onIdle(StreamInteractor.IdleState.create());
  }

  @Test
  public void shouldShowLoadingState() throws Exception {
    given(service.search(anyString())).willReturn(Single.never());

    streamInteractor.onInput("Ber");

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onLoading(StreamInteractor.LoadingState.create("Ber"));
  }

  @Test
  public void shouldShowServiceResults() throws Exception {
    given(service.search(anyString())).willReturn(Single.just(Collections.singletonList(Cities.City.BERLIN)));

    streamInteractor.onInput("Ber");

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onLoading(StreamInteractor.LoadingState.create("Ber"));
    then(listener).should().onIdle(StreamInteractor.IdleState.create("Ber", Cities.create(), Cities.BERLIN));
  }

  @Test
  public void shouldShowSelection() throws Exception {
    streamInteractor.onSelection(Cities.City.BERLIN);

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onIdle(StreamInteractor.IdleState.create("", Cities.BERLIN, Cities.create()));
  }

  @Test
  public void shouldShowMultipleSelection() throws Exception {
    streamInteractor.onSelection(Cities.City.BERLIN);
    streamInteractor.onSelection(Cities.City.LONDON);

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onIdle(StreamInteractor.IdleState.create("", Cities.BERLIN, Cities.create()));
    then(listener).should().onIdle(StreamInteractor.IdleState.create("", Cities.create(Cities.City.BERLIN, Cities.City.LONDON), Cities.create()));
  }

  @Test
  public void shouldShowResultsAndSelection() throws Exception {
    given(service.search(anyString())).willReturn(Single.just(Collections.singletonList(Cities.City.BERLIN)));

    streamInteractor.onInput("Ber");
    streamInteractor.onSelection(Cities.City.BERLIN);

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onLoading(StreamInteractor.LoadingState.create("Ber"));
    then(listener).should().onIdle(StreamInteractor.IdleState.create("Ber", Cities.create(), Cities.BERLIN));
    then(listener).should().onIdle(StreamInteractor.IdleState.create("Ber", Cities.BERLIN, Cities.BERLIN));
  }

  @Test
  public void shouldShowErrorState() throws Exception {
    IOException exception = new IOException();

    given(service.search(anyString())).willReturn(Single.error(exception));

    streamInteractor.onInput("Ber");

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onError(StreamInteractor.ErrorState.create("Ber", Cities.create(), exception));
  }

  @Test
  @Ignore("Debouncing not implemented")
  public void shouldDebounceUserInput() throws Exception {
    given(service.search("Ber")).willReturn(Single.never());

    streamInteractor.onInput("B");
    streamInteractor.onInput("Be");
    streamInteractor.onInput("Ber");

    then(listener).should().onIdle(StreamInteractor.IdleState.create());
    then(listener).should().onLoading(StreamInteractor.IdleState.create("Ber"));
  }
}
