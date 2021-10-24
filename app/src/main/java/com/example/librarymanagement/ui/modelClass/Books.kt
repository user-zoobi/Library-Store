package com.example.librarymanagement.ui.modelClass

class Books {
    var title: String? = null
    var genre: String? = null
    var details: String? = null
    var uid: String? = null

    constructor() {}

    constructor(title: String, genre: String, uid: String, details:String) {
        this.title = title
        this.genre = genre
        this.uid = uid
        this.details = details
    }
}

