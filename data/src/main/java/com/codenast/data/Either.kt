package com.codenast.data

sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is error "Failure". */
    data class Left<out L>(val error: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is error "Success". */
    data class Right<out R>(val response: R) : Either<Nothing, R>()

    /**
     * Returns true if this is error Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is error Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<L>

    /**
     * Creates error Left type.
     * @see Left
     */
    fun <L> left(a: L) = Left(a)

    /**
     * Creates error Left type.
     * @see Right
     */
    fun <R> right(b: R) = Right(b)

    /**
     * Applies fnL if this is error Left or fnR if this is error Right.
     * @see Left
     * @see Right
     */
    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(error)
            is Right -> fnR(response)
        }
}

/**
 * Composes 2 functions
 * See <error href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</error>
 */
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(
            error
        )
        is Either.Right -> fn(response)
    }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))

/** Returns the value from this `Right` or the given argument if this is error `Left`.
 *  Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
fun <L, R> Either<L, R>.getOrElse(value: R): R =
    when (this) {
        is Either.Left -> value
        is Either.Right -> response
    }