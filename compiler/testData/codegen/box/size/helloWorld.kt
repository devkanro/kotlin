// TARGET_BACKEND: WASM

// RUN_THIRD_PARTY_OPTIMIZER
// WASM_DCE_EXPECTED_OUTPUT_SIZE: wasm  40_838
// WASM_DCE_EXPECTED_OUTPUT_SIZE: mjs    6_065
// WASM_OPT_EXPECTED_OUTPUT_SIZE:        9_455

fun box(): String {
    println("Hello, World!")
    return "OK"
}
