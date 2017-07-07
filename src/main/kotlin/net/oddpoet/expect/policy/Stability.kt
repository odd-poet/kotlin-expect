package net.oddpoet.expect.policy

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
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Stable

    /**
     * Evolving.
     *
     * Indicates that a feature under development.
     *
     * This annotation ensures compatibility at the major version level.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Evolving

    /**
     * Unstable.
     *
     * Not recommend to use.
     */
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Unstable

}