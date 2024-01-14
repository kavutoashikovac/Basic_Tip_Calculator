package com.example.basic_tip_calculator

//Subpackage: com.example.hw1_tip.model

class MyObject(val message: String) {
    companion object {
        const val staticMember = "I am static!"

        fun staticMethod() {
            println("Static method called.")
        }
    }

    constructor(message: String, additionalInfo: String) : this(message) {
        // Secondary constructor logic
        println("Secondary constructor called with additionalInfo: $additionalInfo")
    }

    fun memberMethod() {
        println("Member method called.")
    }
}