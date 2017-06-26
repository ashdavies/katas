package com.scout24.dojos.search;

import com.google.auto.value.AutoValue;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.FlowableProcessor;

class StreamInteractor implements Interactor {

  private final FlowableProcessor<String> input = BehaviorProcessor.create();
  private final FlowableProcessor<Cities.City> selection = BehaviorProcessor.create();

  private final Service service;
  private final Listener listener;

  private State state = IdleState.create();

  StreamInteractor(Service service, Listener listener) {
    this.service = service;
    this.listener = listener;

    input.map(input -> LoadingState.create(input, state.selection(), state.results()))
        .doOnNext(onNotify())
        .flatMap(this::search)
        .onErrorReturn(onError())
        .subscribe(onNotify());

    selection.map(city -> IdleState.create(state.input(), state.selection().add(city), state.results()))
        .onErrorReturn(onError())
        .subscribe(onNotify());

    state.notify(listener);
  }

  @Override
  public void onInput(String input) {
    this.input.onNext(input);
  }

  @Override
  public void onSelection(Cities.City selection) {
    this.selection.onNext(selection);
  }

  private Function<Throwable, State> onError() {
    return throwable -> ErrorState.create(state.input(), state.selection(), throwable);
  }

  private Consumer<State> onNotify() {
    return state -> {
      this.state = state;
      state.notify(listener);
    };
  }

  private Flowable<State> search(State state) {
    return service.search(state.input())
        .map(results -> IdleState.create(state.input(), state.selection(), Cities.create(results)))
        .toFlowable();
  }

  interface Listener {

    void onIdle(State state);

    void onLoading(State state);

    void onError(State state);
  }

  @AutoValue
  abstract static class IdleState extends State {

    @Override
    void notify(Listener listener) {
      listener.onIdle(this);
    }

    static State create() {
      return create("");
    }

    static State create(String input) {
      return create(input, Cities.create(), Cities.create());
    }

    static State create(String input, Cities selection, Cities result) {
      return new AutoValue_StreamInteractor_IdleState(input, selection, result);
    }
  }

  @AutoValue
  abstract static class LoadingState extends State {

    @Override
    void notify(Listener listener) {
      listener.onLoading(this);
    }

    static State create(String input) {
      return create(input, Cities.create(), Cities.create());
    }

    static State create(String input, Cities selection, Cities result) {
      return new AutoValue_StreamInteractor_LoadingState(input, selection, result);
    }
  }

  @AutoValue
  abstract static class ErrorState extends State {

    abstract Throwable throwable();

    @Override
    void notify(Listener listener) {
      listener.onError(this);
    }

    static State create(String input, Cities selection, Throwable throwable) {
      return new AutoValue_StreamInteractor_ErrorState(input, selection, Cities.create(), throwable);
    }
  }

  abstract static class State {

    abstract String input();
    abstract Cities selection();
    abstract Cities results();

    abstract void notify(StreamInteractor.Listener listener);
  }
}
