package com.baelding.rxjava;

import rx.Observable;
import rx.observables.BlockingObservable;

public class ObservableImpl {


    public static void main(String[] args) {

        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};

        System.out.println("-------Just-----------");
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe(
                //onNext
                System.out::println,
                //onError
                Throwable::printStackTrace,
                //onCompleted
                () -> System.out.println("onCompleted")
        );

        BlockingObservable<String> blockingObservable = observable.toBlocking();

        System.out.println();
        System.out.println("-------Map-----------");
        Observable.from(letters)
                .map((letter) -> {
                    return letter.toUpperCase();
                })
                .subscribe(
                        System.out::print
                );

        System.out.println();
        System.out.println("-------FlatMap-----------");
        Observable.from(letters)
                .flatMap((letter) -> {
                    String[] returnStrings = {letter.toUpperCase(), letter.toLowerCase()};
                    return Observable.from(returnStrings);
                })
                .subscribe(
                        System.out::print
                );

        System.out.println();
        System.out.println("--------Scan----------");
        Observable.from(letters)
                .scan(new StringBuilder(), (buffer, nextLetter) -> {
                    return buffer.append(nextLetter);
                })
                .subscribe((total) -> {
                    System.out.println(total.toString());
                });

        System.out.println();
        System.out.println("------GroubBy------------");
        Observable.from(numbers)
                .groupBy((i) -> {
                    return 0 == (i % 2) ? "EVEN" : "ODD";
                })
                .subscribe((group) -> {
                    group.subscribe((number) -> {
                        System.out.println(group.getKey() + " : " + number);
                    });
                });

        System.out.println();
        System.out.println("-------Filter-----------");
        Observable.from(numbers)
                .filter((i) -> {
                    return (i % 2 == 1);
                })
                .subscribe(
                        System.out::println
                );


        System.out.println("------DefaultIfEmpty------------");
        Observable.empty()
                .defaultIfEmpty("Observable is empty")
                .subscribe(
                        System.out::println
                );



        System.out.println("------DefaultIfEmpty-2-----------");
        Observable.from(letters)
                .defaultIfEmpty("Observable is empty")
                .first()
                .subscribe(System.out::println);

        System.out.println("-------TakeWhile-----------");
        Observable.from(numbers)
                .takeWhile((i) -> {
                    return i < 5;
                })
                .subscribe(System.out::println);


    }
}