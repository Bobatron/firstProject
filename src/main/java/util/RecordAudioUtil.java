package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static gui.AudioPanel.AUDIO_LOCATION;

public class RecordAudioUtil {
    // record duration, in milliseconds
    static final long RECORD_TIME = 3000;

    // path of the wav file
    public static File getFileName(){
        Long timestamp = System.nanoTime();
        return new File(AUDIO_LOCATION+"RecordAudio"+timestamp+".wav");
    }

    // format of audio file
    static AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which audio data is captured
    static TargetDataLine line;

    /**
     * Defines an audio format
     */
    static AudioFormat getAudioFormat() {
        float sampleRate = 24000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Captures the sound and record into a WAV file
     */
    static void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, getFileName());

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    static void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }

    /**
     * Entry to run the program
     */
    public static void recordAudio() {
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                finish();
            }
        });
        stopper.start();
        // start recording
        start();
    }
}
