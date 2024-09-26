# Object Modeling the Film Industry 

*This is an old IST-242 asg from past semesters.*

**NOTE: this is a totally optional project to give you a place to practice object modeling 
and generally a nice sandbox to try out records and crafting immutable types using Java 22+.**==

### Rules (for IST 311) - if you aim to do this using strict-immutability:
1. no setters or mutators in any class (if you have any classes) 
2. all fields should be `final`, all classes should be marked `final` to preclude extension
3. make every type immutable (use records when possible for this) 
4. eschew abstract classes in favor of interfaces.

## Asg 3: Inheritance and Interfaces

In this assignment, we will continue our efforts in modeling movies as well as those who direct, produce, and act in them using inheritance. In particular, the assignment will have you create a base class called `FilmIndustryWorker` with subclasses for `Actor`, `Director`, and `Producer`.  
Your `Movie` class should not have any subclasses.  

### Part 1: Stubbing out a FilmIndustryWorker hierarchy  

Create classes for `FilmIndustryWorker`, `Actor`, `Producer`, and `Director`. For each of these classes you’ll need to define fields, getter methods, and constructors outlined below.

Now do the following:

- the `Actor`, `Producer`, and `Director` should extend `FilmIndustryWorker`.
- create a new top-level enum by right-clicking the blue `src` folder, select `New Java Class >` and then select `Enum` from the dropdown list; name it: `MovieGenre`, then fill it with the following values: `HORROR`, `SCIFI`, `ACTION`, `DRAMA`, `ROMANCE`, and `COMEDY`.
- the `FilmIndustryWorker` must include an abstract method called `specializesInGenre` which takes as a parameter a `MovieGenre` and returns a `boolean`.
- each subclass must call the `super(..)` constructor.
- each class should define a `toString()` method (it’s ok if you choose to only override `toString()` in the `FilmIndustryWorker` base class).

Here are some details on what each class should store:

#### The `Director` should be able to store:

- first and last name
- the number of years active in the film industry
- list of awards won (this can just be a list of strings)
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
- a list of the awards won (this can just be a list of strings).
- the number of years active in the film industry.

The actor can always specialize in action and romance. They can only specialize in horror or sci-fi if they have more than ten years of industry experience. And they can only specialize in comedy 
and drama if their preferred acting style is `improv` or `character`.

### Part 2: Creating a `Movie` class

The `Movie` class should be able to store the following as fields:

- the title of the movie
- the `Director` of the movie
- the `Producer` of the movie
- the `int` year in which it was released
- the `MPAARating` of the movie (you can reuse the enum from your previous assignment for this)
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
