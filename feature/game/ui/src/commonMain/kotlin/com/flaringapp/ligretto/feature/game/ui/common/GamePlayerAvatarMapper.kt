package com.flaringapp.ligretto.feature.game.ui.common

import com.flaringapp.ligretto.core.ui.components.UiPlayerAvatarType
import com.flaringapp.ligretto.feature.game.model.PlayerAvatar

internal fun PlayerAvatar.toUiAvatar(): UiPlayerAvatarType = when (this) {
    PlayerAvatar.Goober -> UiPlayerAvatarType.Goober
    PlayerAvatar.Scout -> UiPlayerAvatarType.Scout
    PlayerAvatar.Corky -> UiPlayerAvatarType.Corky
    PlayerAvatar.Leo -> UiPlayerAvatarType.Leo
    PlayerAvatar.Dax -> UiPlayerAvatarType.Dax
    PlayerAvatar.Dot -> UiPlayerAvatarType.Dot
    PlayerAvatar.Fluffy -> UiPlayerAvatarType.Fluffy
    PlayerAvatar.Fritz -> UiPlayerAvatarType.Fritz
    PlayerAvatar.Benny -> UiPlayerAvatarType.Benny
    PlayerAvatar.Coco -> UiPlayerAvatarType.Coco
    PlayerAvatar.Patch -> UiPlayerAvatarType.Patch
    PlayerAvatar.Rex -> UiPlayerAvatarType.Rex
    PlayerAvatar.Earl -> UiPlayerAvatarType.Earl
    PlayerAvatar.Rocky -> UiPlayerAvatarType.Rocky
    PlayerAvatar.Mop -> UiPlayerAvatarType.Mop
    PlayerAvatar.Pip -> UiPlayerAvatarType.Pip
}

internal fun UiPlayerAvatarType.toDomainAvatar(): PlayerAvatar = when (this) {
    UiPlayerAvatarType.Goober -> PlayerAvatar.Goober
    UiPlayerAvatarType.Scout -> PlayerAvatar.Scout
    UiPlayerAvatarType.Corky -> PlayerAvatar.Corky
    UiPlayerAvatarType.Leo -> PlayerAvatar.Leo
    UiPlayerAvatarType.Dax -> PlayerAvatar.Dax
    UiPlayerAvatarType.Dot -> PlayerAvatar.Dot
    UiPlayerAvatarType.Fluffy -> PlayerAvatar.Fluffy
    UiPlayerAvatarType.Fritz -> PlayerAvatar.Fritz
    UiPlayerAvatarType.Benny -> PlayerAvatar.Benny
    UiPlayerAvatarType.Coco -> PlayerAvatar.Coco
    UiPlayerAvatarType.Patch -> PlayerAvatar.Patch
    UiPlayerAvatarType.Rex -> PlayerAvatar.Rex
    UiPlayerAvatarType.Earl -> PlayerAvatar.Earl
    UiPlayerAvatarType.Rocky -> PlayerAvatar.Rocky
    UiPlayerAvatarType.Mop -> PlayerAvatar.Mop
    UiPlayerAvatarType.Pip -> PlayerAvatar.Pip
}
