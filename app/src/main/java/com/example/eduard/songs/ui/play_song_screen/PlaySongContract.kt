package com.example.eduard.songs.ui.play_song_screen

import com.example.eduard.songs.model.Song
import com.example.eduard.songs.ui.base.BasePresenter
import com.example.eduard.songs.ui.base.BaseView


interface PlaySongContract {
    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
        fun getSong(songId: Int): Song?
        fun removeSong(item: Song)
        fun addSong(item: Song)
    }
}