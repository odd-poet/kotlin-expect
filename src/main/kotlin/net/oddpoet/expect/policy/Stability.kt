package net.oddpoet.expect.policy

import java.lang.annotation.Documented

/**
 * Stability annotation.
 *
 * It is used to indicate the maturity of a class, interface or method.
 *
 */
internal interface Stability {
    /**
     * Stable.
     *
     * This annotation ensures compatibility at the major version level.
     */
    @Documented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Stable

    /**
     * Evolving.
     *
     * Indicates that a feature under development.
     *
     * This annotation ensures compatibility at the major version level.
     */
    @Documented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Evolving

    /**
     * Unstable.
     *
     * Not recommend to use.
     */
    @Documented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Unstable

}