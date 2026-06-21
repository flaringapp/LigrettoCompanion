package com.flaringapp.ligretto.feature.game.model

enum class PlayerAvatar(
    val id: String,
) {
    Goober("goober"),
    Scout("scout"),
    Corky("corky"),
    Leo("leo"),
    Dax("dax"),
    Dot("dot"),
    Fluffy("fluffy"),
    Fritz("fritz"),
    Benny("benny"),
    Coco("coco"),
    Patch("patch"),
    Rex("rex"),
    Earl("earl"),
    Rocky("rocky"),
    Mop("mop"),
    Pip("pip"),
    ;

    companion object {
        private val AvatarsById = entries.associateBy { it.id }
        fun fromId(id: String): PlayerAvatar? = AvatarsById[id]
    }
}
