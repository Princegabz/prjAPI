package com.example.prjapi

var arrName = ArrayList<String>()
var arrSurname = ArrayList<String>()
var arrPlanID = ArrayList<String>()
var arrAmount = ArrayList<String>()

class User
    (
    val Name:String,
    val Surname:String,
    val PlanID: String,
    val Amount: String
    )

data class Result(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<User>
)
