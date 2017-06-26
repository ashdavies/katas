RxJava Kata
===========

Acceptance Criteria
---------------------

 - You must build a system that can be used to search for cities, and select cities from the results.
 - The system must be reactive and manage state accordingly.

Requirements
------------

 - Start with an idle state
 - Show a loading state
 - Show the results from the api
 - Show user selections with results
 - React to problems with an error state

Set Up (Java 8)
---------------

## RxJava 2
`io.reactivex.rxjava2:rxjava:2.0.6`

## RxJava 1
`io.reactivex:rxjava:1.2.7`

---

RxJava Difference
-----------------

 - Observable -> Flowable
 - Subject -> Processor

 - Cannot emit null
 - Back-pressure support

Rules
-----

You may **not** modify `Interactor`

No conditionals (if/else/switch)
No iterations (for/do/while)

Wrap all primitives and Strings
First class collections

No abbreviations
Simple naming

Keep all entities small (50 lines)
**Static** inner classes permitted
Lambdas and method refs permitted

Tips
----

 - Manage state as a value
 - Use stream pipelines
 - Use event based behaviour

Additional Requirements
-----------------------

 - Debounce user input every 500 ms
