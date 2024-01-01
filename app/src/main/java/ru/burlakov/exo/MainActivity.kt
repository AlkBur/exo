package ru.burlakov.exo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView


class MainActivity : AppCompatActivity() {
    lateinit var playerView: StyledPlayerView
    lateinit var player: ExoPlayer
    //val rtspUri = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    val rtspUri = "rtsp://admin:<password>@<ip_address>:554/streaming/channels/1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)
        // Create a player instance.
        val player = ExoPlayer.Builder(this).build()
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                // Re-initialize player at the current live window default position.
                //player.seekToDefaultPosition()
                player.prepare()
                player.setPlayWhenReady(true);
                //player.play()

            }
        })
        playerView.player = player

        // Create an RTSP media source pointing to an RTSP uri.
        val mediaSource: MediaSource = RtspMediaSource.Factory()
            .setForceUseRtpTcp(true)
            .createMediaSource(MediaItem.fromUri(rtspUri))

        // Set the media item to be played.
        //player.setMediaItem(MediaItem.fromUri(rtspUri))
        player.setMediaSource(mediaSource);
        // Prepare the player.
        player.prepare()
        player.setPlayWhenReady(true);
        //player.play()

    }

    override fun onDestroy() {
        super.onDestroy()
        playerView.onPause();
        player.release();
    }
}