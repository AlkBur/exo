package ru.burlakov.exo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.rtsp.RtspMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {
    lateinit var playerVew: PlayerView
    lateinit var player: ExoPlayer
    //val rtspUri = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    val rtspUri = "rtsp://admin:<password>@<ip_address>:554/streaming/channels/1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerVew = findViewById(R.id.player_view)
        // Create a player instance.
        val player = ExoPlayer.Builder(this).build()
        playerVew.player = player

        // Create an RTSP media source pointing to an RTSP uri.
        val mediaSource: MediaSource = RtspMediaSource.Factory()
            .setForceUseRtpTcp(true)
            .createMediaSource(MediaItem.fromUri(rtspUri))

        // Set the media item to be played.
        //player.setMediaItem(MediaItem.fromUri(rtspUri))
        player.setMediaSource(mediaSource);
        // Prepare the player.
        player.prepare()
        player.play()

    }
}