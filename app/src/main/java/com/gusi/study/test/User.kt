package com.gusi.study.test

/**
 * Created by batue on 2017/10/8.
 */
interface User{
    val nickName :String
}
class PrivateUser(override val nickName: String) :User{
    var counter : Int = 0
    private set(value) {
        counter = value
    }
//    private get() {
//        0
//    }
}
class Subuser(val email:String) :User{
    override val nickName: String
        get() = ""
    fun test(){
        var privateUser = PrivateUser("")
//        privateUser.counter = 2
    }

}