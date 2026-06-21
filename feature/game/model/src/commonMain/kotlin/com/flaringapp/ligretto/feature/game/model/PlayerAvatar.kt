package com.flaringapp.ligretto.feature.game.model

enum class PlayerAvatar(val id: String) {
    Goober("goober"),
    Scout("scout"),
    Corky("corky"),
    Leo("leo");

    companion object {
        private val AvatarsById = entries.associateBy { it.id }
        fun fromId(id: String): PlayerAvatar? = AvatarsById[id]
    }
}
