# bowl

Experimental rejig of Bewl to use implicits and other integrations.

Main change is that I'd like to be able to work on values of type T whenever there is an
implicitly defined DOT[T] in the ambient topos.
This will also mean that for such types T, U I can compare functions T => U, which will be considered
equivalent to arrows T > U. (Should there be a mechanism to cache the implied arrow-constructions?)
There will probably need to be two definitions of equality, one to return a Boolean and one to return
the relevant "Bowlean" type. Say === and =!=. Or maybe there is .toBool for Bowleans.

Also simplifying by:

- removing the "_ <: ~" legacy type constraint which was only needed temporarily 
    for the topos of monoid actions
- similarly the WRAPPER used in Wrappings was alwasy NO_WRAPPER so we may as well not have it
    ...so maybe don't need makeArrow, or PreArrow
    
- build the caching into the Wrappings trait    
    
- fixing up how topos test fixtures / wrapping layers work (there must be a way to do it more simply)

- improving the algebraic structures code (which worked and was performant but a bit clunky)

    
