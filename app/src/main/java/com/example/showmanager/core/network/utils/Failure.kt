package co.crop2cash.core.network.utils

/**
 * Base class for handling network failures i.e exceptions, errors
 */
sealed class Failure {

    /**
     * 500 Internal server error.
     */
    object ServerError : Failure() {

        /**
         * Called to convert object to String.
         */
        override fun toString(): String =
            "An unknown error has occurred. Please try again later."
    }

    /**
     * No Internet connection.
     */
    object NetworkError : Failure() {

        /**
         * Called to convert object to String.
         */
        override fun toString(): String = "No internet connection!"
    }

    /**
     * Error with response body.
     */
    object DataError : Failure()

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure : Failure()
}
