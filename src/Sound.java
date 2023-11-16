import javax.sound.sampled.*;
public class Sound {

    public static void playSound(String[] morseMessage) throws LineUnavailableException, InterruptedException {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();

        int dotDuration = 200;
        int dashDuration = (int) (1.5 * dotDuration);
        int slashDuration = 2 * dashDuration;
        for(String pattern : morseMessage){
            System.out.println(pattern);
            for(char c : pattern.toCharArray()){
                if(c == '.'){
                    playBeep(sourceDataLine, dotDuration);
                    Thread.sleep(dotDuration);
                }else if(c == '-'){
                    playBeep(sourceDataLine, dashDuration);
                    Thread.sleep(dotDuration);
                }else if(c == '/'){
                    Thread.sleep(slashDuration);
                }
            }
            Thread.sleep(dotDuration);
        }
        sourceDataLine.drain();
        sourceDataLine.stop();
        sourceDataLine.close();
    }

    private static void playBeep(SourceDataLine line, int duration){
        byte[] data = new byte[duration * 44100 / 1000];
        for(int i = 0; i < data.length; i++){
            double angle = i / (44100.0/440) * 2.0 * Math.PI;
            data[i] = (byte) (Math.sin(angle) * 127.0);
        }
        line.write(data, 0, data.length);
    }
}

