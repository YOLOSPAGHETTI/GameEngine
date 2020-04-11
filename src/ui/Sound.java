package ui;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

class Sound extends PlaybackListener implements Runnable
{
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;    

    public Sound(String filePath)
    {
        this.filePath = filePath;
    }

    public void play()
    {
        try
        {
            String urlAsString = "file:///" + this.filePath;
            System.out.println(urlAsString);

            player = new AdvancedPlayer
            (
                new URL(urlAsString).openStream(),
                FactoryRegistry.systemRegistry().createAudioDevice()
            );

            player.setPlayBackListener(this);

            playerThread = new Thread(this, "AudioPlayerThread");

            playerThread.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void stop() {
    	player.stop();
    }

    // PlaybackListener members

    public void playbackStarted(PlaybackEvent playbackEvent)
    {
        //System.out.println("playbackStarted()");
    }

    public void playbackFinished(PlaybackEvent playbackEvent)
    {
        //System.out.println("playbackEnded()");
    }    

    // Runnable members

    public void run()
    {
        try
        {
            player.play();
        }
        catch (JavaLayerException ex)
        {
            ex.printStackTrace();
        }

    }
}