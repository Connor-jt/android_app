package com.example.myprogram

class ObjectWrapper(ob:GameObject? = null) {

    var obj:GameObject? = ob
        get() = field
        private set(value) {} // i have no idea how to make it work otherwise

    fun clear(){obj = null}
}