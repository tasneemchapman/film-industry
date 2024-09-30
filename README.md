# Object Modeling the Film Industry 

*This is an old IST-242 asg from past semesters.*

**NOTE: this is a totally optional project to give you a place to practice object modeling 
and generally a nice sandbox to try out records and crafting immutable types using Java 22+.**

### Rules (for IST 311) - if you aim to do this using strict-immutability:
1. no setters or mutators in any class (if you have any classes) 
2. all fields should be `final`, all classes should be marked `final` to preclude extension
3. make every type immutable (use records when possible for this) 
4. eschew abstract classes in favor of interfaces.

## Asg 3: Inheritance and Interfaces

In this assignment, we will continue our efforts in modeling movies as well as those who direct, produce, and act in them using inheritance. In particular, the assignment will have you create a base class called `FilmIndustryWorker` with subclasses for `Actor`, `Director`, and `Producer`.  
Your `Movie` class should not have any subclasses.  

## Preamble: an aside on enumeration types

An enum in Java defines a fixed set of values. For example, here's an `enum` for movie genres:

```java
enum MovieGenre {
    Horror, SciFi, Romance, Comedy, Drama, Action
}
```

With this enum, you can now create variables of type MovieGenre:

```java
MovieGenre g;
```

And assign one of the predefined (i.e.: *enumerated*) genres:

```java
g = MovieGenre.Romance; // g refers to Romance
g = MovieGenre.Horror;  // g refers to Horror
```

#### why use these? seems like an extra level of complexity...

Without enums, you might write a method that accepts a string for a movie's genre:

```java
public boolean registerFilm(String name, String genre) {
    if (genre.equals("Horror")) {
        // process horror movie
    }
    return true; // movie registered
}
```

This works, but there's a problem: What if someone passes "slasher" instead of "Horror"?

```java
registerFilm("Friday the 13th", "slasher");  // mistake!
```

Because "slasher" is just a string, it won't cause an error, but it's clearly not a valid genre. 
So keeping the `genre` parameter of the `registerFilm` method above makes it very difficult (re: impossible)
to know fully how to implement the `registerFilm` method... as there can be infinitely many strings passed into
the method....:
* "slash"
* "s"
* "sx"
* " slash"
* "" , etc.

Enums provide a nice solution to this issue by restricting input to valid movie genres only. We'll improve this 
(still purely hypothetical) method by using the `MovieGenre` enum we defined above:

```java
public boolean registerFilm(String name, MovieGenre genre) {
    if (genre == MovieGenre.Horror) {
        // process horror movie
    }
    return true; // movie registered
}
```
Now, only valid genres can be passed:

```java
registerFilm("Friday the 13th", MovieGenre.Horror); // correct
```

If someone tries to use an invalid genre:

```java
registerFilm("Friday the 13th", "slasher"); // compile-time error (doesn't even start executing your code)!
```

The compiler catches the mistake, ensuring only valid values from MovieGenre are used.

> TLDR/takeaway: using a 'flat' `String` as the datatype for everything doesn't always yield
> simple or correct code... using java's static type system allows one to enforce certain level of
> compile-time code correctness guarantees..

### Part 1: Stubbing out a FilmIndustryWorker hierarchy  

- have a `Director`, `Producer`, and `Actor`... they should extend `FilmIndustryWorker`.
- the `FilmIndustryWorker` must include abstract method called `specializesInGenre` which takes as a parameter a `MovieGenre` and returns a `boolean`.
- each subclass must call the `super(..)` constructor.
- each class should define a `toString()` method (it’s ok if you choose to only override `toString()` in the `FilmIndustryWorker` base class).

Here are some details on what each class should store:

#### The `Director` should be able to store:

- first and last name
- the number of years active in the film industry
- whether they use digital or film technologies

The `specializesInGenre(..)` method for the director should only return `true` if the movie genre passed into the method is romance, comedy, horror, action, or drama; they can specialize in sci-fi, but only if they employ digital film technologies.

#### The `Producer` should be able to store:

- first and last name
- the number of years active in the film industry
- list of awards won (this can just be a list of strings)

The producer can produce films in any genre with the exception of sci-fi 
(they can only specialize in this if they have spent more than 5 years in the film industry).

#### The `Actor` should be able to store:

- first and last name
- which type of acting they specialize in; define an enum at the top of this class called `ActingStyle` to represent the datatype for this field; the enum should contain the values `METHOD`, `CHARACTER`, and `IMPROV`.
- the number of years active in the film industry.

The actor can always specialize in action and romance. They can only specialize in horror or sci-fi if they have more than ten years of industry experience. And they can only specialize in comedy 
and drama if their preferred acting style is `improv` or `character`.

### Part 2: Creating a `Movie` class

The `Movie` class should be able to store the following as fields:

- the title of the movie
- the `Director` of the movie
- the `Producer` of the movie
- the `int` year in which it was released
- the `MPAARating` of the movie (here `MPAARating` should be another enum type containing values R, PG, G, )
- a list of `Actor`s starring in the movie
- the genre of the movie (use your new `MovieGenre` enum for this)

Info for all of these fields (except the actors list) should be passed in as parameters to the constructor and assigned to the corresponding fields described above.

In the constructor, you should also do a check to see if the director specializes in the provided genre of the movie (if they don’t, then throw a new `IllegalArgumentException`) — do the same for the producer.

Don’t forget to initialize an empty list of `Actors` starring in the movie. This can be done either in the constructor or at the site of the private field above the constructor.

Instead of passing a list of actor objects into the constructor, provide a method called `addActor(..)` which takes an `Actor` as a parameter and returns nothing. In the method, before adding the given actor to the movie’s list of actors, first check to see if they specialize in the current movie’s genre. Throw an `IllegalArgumentException` if they don’t.

Make sure you provide getter methods so that users can access each field of the object then override the `toString()` method. Here’s what my `toString` produces for the movie “Terminator 2”:

> (1991) Terminator 2: Judgement Day - SCIFI - rated: R

### Part 3: Implementing the `Comparable` interface for Movie objects

Now make your `Movie` class implement the `Comparable` interface. Here’s the relevant change to the header for your `Movie` class:

```java
public class Movie implements Comparable <Movie> { ...
```

Once you add this, you'll need to override/implement the 
interface's `compareTo(..)` method. We'll define the 
natural ordering for our movies by release year. 

The `compareTo(..)` method contract from `Comparable` should 
take a parameter `o` of type `Movie` and should return 
an `int x`, where:

* `x < 0` if this movie's release year "comes before" o.releaseYear
* `x == 0` if this movie's release year equals o.releaseYear
* `x > 0` if this movie's release year "comes after" o.releaseYear

### Part 4: Putting it all together in a Tester class

In your Tester class, you should define a main(..) method and instantiate at least four Movie objects with relevant information. Add each one to a list, then iterate over them—printing each. Immediately after the first loop, call:

> Collections.sort(myMovies);

Lastly, iterate over the movies list again to confirm whether 
the `compareTo(..)` method is working correctly. 

See below for a sample run from my Tester class.

```
BEFORE SORTING:
(2015) The Martian - SCI_FI - rated: PG_13  
(2007) Transformers 1 - ACTION - rated: PG_13  
(1994) The Shawshank Redemption - DRAMA - rated: PG_13  
(2008) The Dark Knight - ACTION - rated: R
AFTER SORTING:
(1994) The Shawshank Redemption - DRAMA - rated: PG_13  
(2007) Transformers 1 - ACTION - rated: PG_13  
(2008) The Dark Knight - ACTION - rated: R  
(2015) The Martian - SCI_FI - rated: PG_13
```

## Handin

*no handin for this *
