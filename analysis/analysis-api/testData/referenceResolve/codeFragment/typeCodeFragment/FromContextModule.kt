// IGNORE_FE10

// MODULE: context

// FILE: context.kt
fun test() {
    <caret_context>Foo
}

// FILE: other.kt
object Foo


// MODULE: main
// MODULE_KIND: CodeFragment

// FILE: fragment.kt
// CODE_FRAGMENT_KIND: TYPE
<caret>Foo